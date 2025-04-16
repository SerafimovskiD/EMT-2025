package mk.ukim.finki.lab1.service.Application.impl;

import mk.ukim.finki.lab1.dto.CreateHostDto;
import mk.ukim.finki.lab1.dto.DisplayHostDto;
import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.model.exceptions.HostException;
import mk.ukim.finki.lab1.service.Application.HostApplicationService;
import mk.ukim.finki.lab1.service.Domain.CountryService;
import mk.ukim.finki.lab1.service.Domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream().map(DisplayHostDto::from).toList();
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto createHostDto) {
        Optional<Country> country = countryService.findById(createHostDto.country());
        if (country.isPresent()){
            return hostService.save(createHostDto.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        Optional<Country> country = countryService.findById(createHostDto.country());
        return hostService.update(id,createHostDto.toHost(country.orElse(null))).map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) throws HostException {
        hostService.deleteById(id);
    }
}
