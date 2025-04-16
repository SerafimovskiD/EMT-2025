package mk.ukim.finki.lab1.service.Domain;

import mk.ukim.finki.lab1.model.domain.Accommodation;
import mk.ukim.finki.lab1.model.domain.TempReservation;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;

import java.util.List;
import java.util.Optional;

public interface TempReservationService {
    List<Accommodation> listAllAccommodationInTempReservation(Long reservationId);
    Optional<TempReservation> getActiveTempReservation(String username);
    Optional<TempReservation> addAccommodationToTempReservation(String username,Long accommodationId) throws AccommodationException;
    Optional<TempReservation> confirmTempReservation(Long reservationId);
    void clearTempReservationList(Long reservationId);
}
