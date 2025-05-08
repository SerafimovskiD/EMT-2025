package mk.ukim.finki.lab.service.Application;

import mk.ukim.finki.lab.dto.CreateCountryDto;
import mk.ukim.finki.lab.dto.DisplayCountryDto;
import mk.ukim.finki.lab.model.exceptions.CountryException;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> update(Long id,CreateCountryDto createCountryDto);
    void deleteById(Long id) throws CountryException;


}
