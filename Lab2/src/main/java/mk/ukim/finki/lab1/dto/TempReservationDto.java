package mk.ukim.finki.lab1.dto;

import mk.ukim.finki.lab1.model.domain.ReservationStatus;
import mk.ukim.finki.lab1.model.domain.TempReservation;

import java.time.LocalDateTime;
import java.util.List;

public record TempReservationDto(Long id, LocalDateTime dateCreated, DisplayUserDto user,
                                 List<DisplayAccommodationDto> accommodations,
                                 ReservationStatus status) {
    public static TempReservationDto from(TempReservation tempReservation){
        return new TempReservationDto(
                tempReservation.getId(),
                tempReservation.getDateCreated(),
                DisplayUserDto.from(tempReservation.getUser()),
                DisplayAccommodationDto.from(tempReservation.getAccommodations()),
                tempReservation.getStatus()
        );
    }
}
