package mk.ukim.finki.lab1.service.Domain.impl;

import mk.ukim.finki.lab1.model.exceptions.CountryException;
import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.repository.CountryRepository;
import mk.ukim.finki.lab1.service.Domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(Country country) {
       if(country.getName()!=null && country.getContinent()!=null){
           return Optional.of(countryRepository.save(new Country(country.getName(), country.getContinent())));
       }
       return Optional.empty();
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id).map(existingCountry->{
           if(country.getName()!=null){
               existingCountry.setName(country.getName());
           }
           if(country.getContinent()!=null){
               existingCountry.setContinent(country.getContinent());
           }
           return countryRepository.save(existingCountry);
        });
    }

    @Override
    public void deleteById(Long id) throws CountryException {
        Country country=countryRepository.findById(id).orElseThrow(()->new CountryException(id));
        countryRepository.delete(country);
    }
}
