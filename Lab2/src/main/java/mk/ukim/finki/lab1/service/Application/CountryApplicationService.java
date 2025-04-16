package mk.ukim.finki.lab1.service.Application;

import mk.ukim.finki.lab1.dto.CreateCountryDto;
import mk.ukim.finki.lab1.dto.DisplayCountryDto;
import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.model.exceptions.CountryException;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> update(Long id,CreateCountryDto createCountryDto);
    void deleteById(Long id) throws CountryException;


}
