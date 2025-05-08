package mk.ukim.finki.lab.service.Domain.impl;

import mk.ukim.finki.lab.model.exceptions.HostException;
import mk.ukim.finki.lab.model.domain.Host;
import mk.ukim.finki.lab.model.projections.HostNameProjection;
import mk.ukim.finki.lab.repository.HostPerCountryViewRepository;
import mk.ukim.finki.lab.repository.HostRepository;
import mk.ukim.finki.lab.service.Domain.CountryService;
import mk.ukim.finki.lab.service.Domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostPerCountryViewRepository hostPerCountryViewRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostPerCountryViewRepository hostPerCountryViewRepository) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostPerCountryViewRepository = hostPerCountryViewRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();
       if(host.getName()!=null && host.getSurname()!=null &&
               countryService.findById(host.getCountry().getId()).isPresent()){
           savedHost = Optional.of(hostRepository.save(new Host(
                   host.getName(),
                   host.getSurname(),
                   countryService.findById(host.getCountry().getId()).get())));
           this.refreshMaterializedView();
//            this.applicationEventPublisher.publishEvent(new HostCreatedEvent(host));
       }
       return savedHost;
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
      return hostRepository.findById(id).map(eh->{
          if(host.getName()!=null){
              eh.setName(host.getName());
          }
          if(host.getSurname()!=null){
              eh.setSurname(host.getSurname());
          }
          if(host.getCountry()!=null && countryService.findById(host.getCountry().getId()).isPresent()){
              eh.setCountry(countryService.findById(host.getCountry().getId()).get());
          }
          Host updatedHost = hostRepository.save(eh);
          this.refreshMaterializedView();
//          this.applicationEventPublisher.publishEvent(new HostUpdatedEvent(host));
          return updatedHost;
      });
    }

    @Override
    public void deleteById(Long id) throws HostException {
        Host host=hostRepository.findById(id).orElseThrow(()->new HostException(id));
        hostRepository.delete(host);
        this.refreshMaterializedView();
//        this.applicationEventPublisher.publishEvent(new HostDeletedEvent(host));
    }

    @Override
    public void refreshMaterializedView() {
        hostPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostNameProjection> getAllHostName() {
        return hostRepository.findAllBy();
    }
}
