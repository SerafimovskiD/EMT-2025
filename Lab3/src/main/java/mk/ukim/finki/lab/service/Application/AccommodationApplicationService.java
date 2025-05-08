package mk.ukim.finki.lab.service.Application;

import mk.ukim.finki.lab.dto.CreateAccommodationDto;
import mk.ukim.finki.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab.model.exceptions.AccommodationException;
import mk.ukim.finki.lab.model.views.AccommodationsPerHostView;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> findById(Long id);
    Optional<DisplayAccommodationDto>save(CreateAccommodationDto createAccommodationDto);
    Optional<DisplayAccommodationDto>update(Long id,CreateAccommodationDto createAccommodationDto);
    void deleteById(Long id) throws AccommodationException;
    Optional<DisplayAccommodationDto>rent(Long id);

    List<AccommodationsPerHostView> getAccommodationsPerHost();

}
