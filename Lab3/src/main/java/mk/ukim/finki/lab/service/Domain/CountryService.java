package mk.ukim.finki.lab.service.Domain;

import mk.ukim.finki.lab.model.exceptions.CountryException;
import mk.ukim.finki.lab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(Country country);
    Optional<Country> update(Long id,Country country);
    void deleteById(Long id) throws CountryException;


}
