package mk.ukim.finki.lab1.service.Domain.impl;

import mk.ukim.finki.lab1.model.exceptions.HostException;
import mk.ukim.finki.lab1.model.domain.Host;
import mk.ukim.finki.lab1.repository.HostRepository;
import mk.ukim.finki.lab1.service.Domain.CountryService;
import mk.ukim.finki.lab1.service.Domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
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
       if(host.getName()!=null && host.getSurname()!=null &&
               countryService.findById(host.getCountry().getId()).isPresent()){
           return Optional.of(hostRepository.save(new Host(
                   host.getName(),
                   host.getSurname(),
                   countryService.findById(host.getCountry().getId()).get())));
       }
       return Optional.empty();
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
          return hostRepository.save(eh);
      });
    }

    @Override
    public void deleteById(Long id) throws HostException {
        Host host=hostRepository.findById(id).orElseThrow(()->new HostException(id));
        hostRepository.delete(host);
    }
}
