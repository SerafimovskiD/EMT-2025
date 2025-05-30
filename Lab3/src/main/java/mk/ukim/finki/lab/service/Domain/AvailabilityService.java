package mk.ukim.finki.lab.service.Domain;


import mk.ukim.finki.lab.model.domain.Availability;

import java.util.List;
import java.util.Optional;

public interface AvailabilityService {
    List<Availability> findAll();

    Optional<Availability> findById(Long id);

    Optional<Availability> update(Long id, Availability availability);

    Optional<Availability> save(Availability availability);

    void deleteById(Long id);
    List<Availability> findByAccommodationId(Long accommodationId);

}
