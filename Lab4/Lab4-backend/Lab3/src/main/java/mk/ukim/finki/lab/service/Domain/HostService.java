package mk.ukim.finki.lab.service.Domain;

import mk.ukim.finki.lab.model.exceptions.HostException;
import mk.ukim.finki.lab.model.domain.Host;
import mk.ukim.finki.lab.model.projections.HostNameProjection;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host>findById(Long id);
    Optional<Host>save(Host host);
    Optional<Host>update(Long id,Host host);
    void deleteById(Long id) throws HostException;
    void refreshMaterializedView();

    List<HostNameProjection> getAllHostName();


}
