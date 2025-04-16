package mk.ukim.finki.lab1.service.Domain.impl;

import mk.ukim.finki.lab1.model.domain.Accommodation;
import mk.ukim.finki.lab1.model.domain.ReservationStatus;
import mk.ukim.finki.lab1.model.domain.TempReservation;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.exceptions.AccommodationAlreadyInTempReservationException;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;
import mk.ukim.finki.lab1.model.exceptions.TempReservationNotFoundException;
import mk.ukim.finki.lab1.repository.TempReservationRepository;
import mk.ukim.finki.lab1.service.Domain.AccommodationService;
import mk.ukim.finki.lab1.service.Domain.TempReservationService;
import mk.ukim.finki.lab1.service.Domain.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TempReservationServiceImpl implements TempReservationService {

    private final TempReservationRepository tempReservationRepository;
    private final AccommodationService accommodationService;
    private final UserService userService;

    public TempReservationServiceImpl(TempReservationRepository tempReservationRepository, AccommodationService accommodationService, UserService userService) {
        this.tempReservationRepository = tempReservationRepository;
        this.accommodationService = accommodationService;
        this.userService = userService;
    }


    @Override
    public List<Accommodation> listAllAccommodationInTempReservation(Long reservationId) {
        if (tempReservationRepository.findById(reservationId).isEmpty()){
            throw new TempReservationNotFoundException(reservationId);
        }
        return tempReservationRepository.findById(reservationId).get().getAccommodations();
    }

    @Override
    public Optional<TempReservation> getActiveTempReservation(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(tempReservationRepository.findByUserAndStatus(
                user,
                ReservationStatus.PENDING
        ).orElseGet(() -> tempReservationRepository.save(new TempReservation(user))));

    }

    @Override
    public Optional<TempReservation> addAccommodationToTempReservation(String username, Long accommodationId) throws AccommodationException {
        if (getActiveTempReservation(username).isPresent()) {
            TempReservation tempReservation = getActiveTempReservation(username).get();

            Accommodation accommodation = accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationException(accommodationId));
            if (!tempReservation.getAccommodations().stream().filter(i -> i.getId().equals(accommodationId)).toList().isEmpty())
                throw new AccommodationAlreadyInTempReservationException(accommodationId, username);
            tempReservation.getAccommodations().add(accommodation);
            return Optional.of(tempReservationRepository.save(tempReservation));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<TempReservation> confirmTempReservation(Long reservationId) {
        TempReservation reservationList = this.tempReservationRepository.findById(reservationId)
                .orElseThrow(() -> new TempReservationNotFoundException(reservationId));

        // Mark all accommodations as rented
        reservationList.getAccommodations().forEach(accommodation -> {
            accommodation.setRented(true);
            this.accommodationService.save(accommodation);
        });

        // Change status to CONFIRMED
        reservationList.setStatus(ReservationStatus.CONFIRMED);
        return Optional.of(this.tempReservationRepository.save(reservationList));
    }

    @Override
    public void clearTempReservationList(Long reservationId) {
        TempReservation reservationList = this.tempReservationRepository.findById(reservationId)
                .orElseThrow(() -> new TempReservationNotFoundException(reservationId));

        reservationList.getAccommodations().clear();
        this.tempReservationRepository.save(reservationList);
    }
}
