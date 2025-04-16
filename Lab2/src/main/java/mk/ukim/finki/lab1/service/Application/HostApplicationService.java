package mk.ukim.finki.lab1.service.Application;

import mk.ukim.finki.lab1.dto.CreateHostDto;
import mk.ukim.finki.lab1.dto.DisplayHostDto;
import mk.ukim.finki.lab1.model.exceptions.HostException;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();
    Optional<DisplayHostDto> findById(Long id);
    Optional<DisplayHostDto>save(CreateHostDto createHostDto);
    Optional<DisplayHostDto>update(Long id, CreateHostDto createHostDto);
    void deleteById(Long id) throws HostException;
}
