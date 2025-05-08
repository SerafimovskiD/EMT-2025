package mk.ukim.finki.lab.service.Application;

import mk.ukim.finki.lab.dto.CreateHostDto;
import mk.ukim.finki.lab.dto.DisplayHostDto;
import mk.ukim.finki.lab.model.exceptions.HostException;
import mk.ukim.finki.lab.model.views.HostPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();
    Optional<DisplayHostDto> findById(Long id);
    Optional<DisplayHostDto>save(CreateHostDto createHostDto);
    Optional<DisplayHostDto>update(Long id, CreateHostDto createHostDto);
    void deleteById(Long id) throws HostException;

    List<HostPerCountryView> getHostPerCountry();


}
