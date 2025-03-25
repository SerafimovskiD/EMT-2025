package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.exceptions.AccommodationException;
import mk.ukim.finki.lab1.model.Accommodation;
import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.dto.AccommodationDto;
import mk.ukim.finki.lab1.repository.AccommodationRepository;
import mk.ukim.finki.lab1.service.AccommodationService;
import mk.ukim.finki.lab1.service.CountryService;
import mk.ukim.finki.lab1.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final CountryService countryService;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, CountryService countryService, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.countryService = countryService;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodationDto) {
        if(accommodationDto.getName()!=null && accommodationDto.getCategory()!=null
                && accommodationDto.getNumRooms()!=null
                && hostService.findById(accommodationDto.getHost()).isPresent()){
                return Optional.of(accommodationRepository.save(new Accommodation(
                        accommodationDto.getName(),
                        accommodationDto.getCategory(),
                        hostService.findById(accommodationDto.getHost()).get(),
                        accommodationDto.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodationDto) {
        return accommodationRepository.findById(id).map(ax -> {
            if (accommodationDto.getName() != null) {
                ax.setName(accommodationDto.getName());
            }
            if (accommodationDto.getCategory() != null) {
                ax.setCategory(accommodationDto.getCategory());
            }
            if (accommodationDto.getNumRooms() != null) {
                ax.setNumRooms(accommodationDto.getNumRooms());
            }
            if (accommodationDto.getHost() != null && hostService.findById(accommodationDto.getHost()).isPresent()) {
                ax.setHost(hostService.findById(accommodationDto.getHost()).get());
            }
            return accommodationRepository.save(ax);
        });
    }

    @Override
    public void deleteById(Long id) throws AccommodationException {
     Accommodation accommodation=accommodationRepository.findById(id).orElseThrow(()->new AccommodationException(id));
     accommodationRepository.delete(accommodation);
    }

    @Override
    public Optional<Accommodation> rent(Long id) {
        return accommodationRepository.findById(id).map(r->{
            if(r.getNumRooms()>0){
                r.setNumRooms(r.getNumRooms()-1);
            }
            return accommodationRepository.save(r);
        });
    }

//    @Override
//    public Optional<Accommodation> findByName(String name) {
//        return accommodationRepository.findByName(name);
//    }
//
//    @Override
//    public Optional<Accommodation> findByCategory(Category category) {
//        return accommodationRepository.findByCategory(category);    }


}
