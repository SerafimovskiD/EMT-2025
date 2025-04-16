package mk.ukim.finki.lab1.service.Domain.impl;

import mk.ukim.finki.lab1.model.domain.Accommodation;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;
import mk.ukim.finki.lab1.repository.AccommodationRepository;

import mk.ukim.finki.lab1.service.Domain.AccommodationService;
import mk.ukim.finki.lab1.service.Domain.CountryService;
import mk.ukim.finki.lab1.service.Domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
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
    public Optional<Accommodation> save(Accommodation accommodation) {
        if(accommodation.getName()!=null && accommodation.getCategory()!=null
                && accommodation.getNumRooms()!=null
                && hostService.findById(accommodation.getHost().getId()).isPresent()){
                return Optional.of(accommodationRepository.save(new Accommodation(
                        accommodation.getName(),
                        accommodation.getCategory(),
                        hostService.findById(accommodation.getHost().getId()).get(),
                        accommodation.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(ax -> {
            if (accommodation.getName() != null) {
                ax.setName(accommodation.getName());
            }
            if (accommodation.getCategory() != null) {
                ax.setCategory(accommodation.getCategory());
            }
            if (accommodation.getNumRooms() != null) {
                ax.setNumRooms(accommodation.getNumRooms());
            }
            if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
                ax.setHost(hostService.findById(accommodation.getHost().getId()).get());
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
}
