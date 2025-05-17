package mk.ukim.finki.lab.service.Application;


import mk.ukim.finki.lab.dto.CreateAvailabilityDto;
import mk.ukim.finki.lab.dto.DisplayAvailabilityDto;
import java.util.List;
import java.util.Optional;

public interface ApplicationAvailabilityService {
    List<DisplayAvailabilityDto> findAll();

    Optional<DisplayAvailabilityDto> findById(Long id);

    Optional<DisplayAvailabilityDto> update(Long id, CreateAvailabilityDto availability);

    Optional<DisplayAvailabilityDto> save(CreateAvailabilityDto availability);

    void deleteById(Long id);
    List<DisplayAvailabilityDto> findByAccommodationId(Long accommodationId);
}