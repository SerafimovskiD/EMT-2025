package mk.ukim.finki.lab1.service.Domain;

import mk.ukim.finki.lab1.model.exceptions.HostException;
import mk.ukim.finki.lab1.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host>findById(Long id);
    Optional<Host>save(Host host);
    Optional<Host>update(Long id,Host host);
    void deleteById(Long id) throws HostException;
}
