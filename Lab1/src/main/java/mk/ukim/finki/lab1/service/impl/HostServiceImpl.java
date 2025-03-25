package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.exceptions.HostException;
import mk.ukim.finki.lab1.model.Host;
import mk.ukim.finki.lab1.model.dto.HostDto;
import mk.ukim.finki.lab1.repository.HostRepository;
import mk.ukim.finki.lab1.service.CountryService;
import mk.ukim.finki.lab1.service.HostService;
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
    public Optional<Host> save(HostDto hostDto) {
       if(hostDto.getName()!=null && hostDto.getSurname()!=null &&
               countryService.findById(hostDto.getCountry()).isPresent()){
           return Optional.of(hostRepository.save(new Host(
                   hostDto.getName(),
                   hostDto.getSurname(),
                   countryService.findById(hostDto.getCountry()).get())));
       }
       return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, HostDto hostDto) {
      return hostRepository.findById(id).map(eh->{
          if(hostDto.getName()!=null){
              eh.setName(hostDto.getName());
          }
          if(hostDto.getSurname()!=null){
              eh.setSurname(hostDto.getSurname());
          }
          if(hostDto.getCountry()!=null && countryService.findById(hostDto.getCountry()).isPresent()){
              eh.setCountry(countryService.findById(hostDto.getCountry()).get());
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
