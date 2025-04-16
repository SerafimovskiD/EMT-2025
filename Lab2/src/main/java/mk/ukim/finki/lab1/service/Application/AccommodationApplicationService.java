package mk.ukim.finki.lab1.service.Application;

import mk.ukim.finki.lab1.dto.CreateAccommodationDto;
import mk.ukim.finki.lab1.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> findById(Long id);
    Optional<DisplayAccommodationDto>save(CreateAccommodationDto createAccommodationDto);
    Optional<DisplayAccommodationDto>update(Long id,CreateAccommodationDto createAccommodationDto);
    void deleteById(Long id) throws AccommodationException;
    Optional<DisplayAccommodationDto>rent(Long id);

}
