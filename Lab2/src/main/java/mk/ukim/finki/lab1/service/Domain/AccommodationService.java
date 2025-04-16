package mk.ukim.finki.lab1.service.Domain;

import mk.ukim.finki.lab1.dto.CreateAccommodationDto;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;
import mk.ukim.finki.lab1.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation>findAll();
    Optional<Accommodation>findById(Long id);
    Optional<Accommodation>save(Accommodation accommodation);
    Optional<Accommodation>update(Long id, Accommodation accommodation);
    void deleteById(Long id) throws AccommodationException;
    Optional<Accommodation>rent(Long id);

}
