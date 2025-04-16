package mk.ukim.finki.lab1.service.Application.impl;

import mk.ukim.finki.lab1.dto.CreateAccommodationDto;
import mk.ukim.finki.lab1.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab1.model.domain.Host;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;
import mk.ukim.finki.lab1.service.Application.AccommodationApplicationService;
import mk.ukim.finki.lab1.service.Domain.AccommodationService;
import mk.ukim.finki.lab1.service.Domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }


    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.host());
        if (host.isPresent()){
            return accommodationService.save(createAccommodationDto.toAccommodation(host.get())).map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.host());
        return accommodationService.update(id,createAccommodationDto.toAccommodation(host.orElse(null)))
                .map(DisplayAccommodationDto::from);

    }

    @Override
    public void deleteById(Long id) throws AccommodationException {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> rent(Long id) {
        return Optional.empty();
    }
}
