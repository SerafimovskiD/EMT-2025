package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.exceptions.AccommodationException;
import mk.ukim.finki.lab1.model.Accommodation;
import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation>findAll();
    Optional<Accommodation>findById(Long id);
    Optional<Accommodation>save(AccommodationDto accommodationDto);
    Optional<Accommodation>update(Long id,AccommodationDto accommodationDto);
    void deleteById(Long id) throws AccommodationException;
    Optional<Accommodation>rent(Long id);
//    Optional<Accommodation> findByName(String name);
//    Optional<Accommodation> findByCategory(Category category);




}
