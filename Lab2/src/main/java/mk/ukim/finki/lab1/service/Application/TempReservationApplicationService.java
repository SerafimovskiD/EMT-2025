package mk.ukim.finki.lab1.service.Application;

import mk.ukim.finki.lab1.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab1.dto.TempReservationDto;
import mk.ukim.finki.lab1.model.domain.TempReservation;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;

import java.util.List;
import java.util.Optional;

public interface TempReservationApplicationService {

    List<DisplayAccommodationDto> listAllAccommodationInTempReservation(Long reservationId);

    Optional<TempReservationDto> getActiveTempReservation(String username);

    Optional<TempReservationDto> addAccommodationToTempReservation(String username, Long accommodationId) throws AccommodationException;

    Optional<TempReservationDto> confirmTempReservation(Long reservationId);

    void clearTempReservationList(Long reservationId);

}
