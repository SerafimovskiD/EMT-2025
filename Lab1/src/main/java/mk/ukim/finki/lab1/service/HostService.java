package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.exceptions.HostException;
import mk.ukim.finki.lab1.model.Host;
import mk.ukim.finki.lab1.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host>findById(Long id);
    Optional<Host>save(HostDto hostDto);
    Optional<Host>update(Long id,HostDto hostDto);
    void deleteById(Long id) throws HostException;
}
