package mk.ukim.finki.lab.service.Application.impl;

import mk.ukim.finki.lab.dto.CreateAccommodationDto;
import mk.ukim.finki.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab.model.domain.Host;
import mk.ukim.finki.lab.model.exceptions.AccommodationException;
import mk.ukim.finki.lab.model.views.AccommodationsPerHostView;
import mk.ukim.finki.lab.repository.AccommodationsPerHostViewRepository;
import mk.ukim.finki.lab.service.Application.AccommodationApplicationService;
import mk.ukim.finki.lab.service.Domain.AccommodationService;
import mk.ukim.finki.lab.service.Domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService, AccommodationsPerHostViewRepository accommodationsPerHostViewRepository) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
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

    @Override
    public List<AccommodationsPerHostView> getAccommodationsPerHost() {
        return accommodationsPerHostViewRepository.findAll();
    }
}
