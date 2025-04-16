package mk.ukim.finki.lab1.service.Application.impl;

import mk.ukim.finki.lab1.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab1.dto.TempReservationDto;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;
import mk.ukim.finki.lab1.service.Application.TempReservationApplicationService;
import mk.ukim.finki.lab1.service.Domain.TempReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TempReservationApplicationServiceImpl implements TempReservationApplicationService {

    private final TempReservationService tempReservationService;

    public TempReservationApplicationServiceImpl(TempReservationService tempReservationService) {
        this.tempReservationService = tempReservationService;
    }


    @Override
    public List<DisplayAccommodationDto> listAllAccommodationInTempReservation(Long reservationId) {
        return DisplayAccommodationDto.from(tempReservationService.listAllAccommodationInTempReservation(reservationId));
    }

    @Override
    public Optional<TempReservationDto> getActiveTempReservation(String username) {
        return tempReservationService.getActiveTempReservation(username).map(TempReservationDto::from);
    }

    @Override
    public Optional<TempReservationDto> addAccommodationToTempReservation(String username, Long accommodationId) throws AccommodationException {
        return tempReservationService.addAccommodationToTempReservation(username, accommodationId).map(TempReservationDto::from);
    }

    @Override
    public Optional<TempReservationDto> confirmTempReservation(Long reservationId) {
        return tempReservationService.confirmTempReservation(reservationId).map(TempReservationDto::from);
    }

    @Override
    public void clearTempReservationList(Long reservationId) {
        this.tempReservationService.clearTempReservationList(reservationId);
    }
}
