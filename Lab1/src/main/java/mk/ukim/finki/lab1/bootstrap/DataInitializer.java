package mk.ukim.finki.lab1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.Country;
import mk.ukim.finki.lab1.model.Host;
import mk.ukim.finki.lab1.model.dto.AccommodationDto;
import mk.ukim.finki.lab1.repository.AccommodationRepository;
import mk.ukim.finki.lab1.repository.CountryRepository;
import mk.ukim.finki.lab1.repository.HostRepository;
import mk.ukim.finki.lab1.web.AccommodationController;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationController accommodationController;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializer(AccommodationController accommodationController, CountryRepository countryRepository, HostRepository hostRepository) {
        this.accommodationController = accommodationController;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }
   @PostConstruct
    public void init(){
        Country c1=countryRepository.save(new Country("Macedonia","Europe"));
        Country c2=countryRepository.save(new Country("France","Europe"));

        Host h1=hostRepository.save(new Host("Damjan","Serafimovski",c1));
        Host h2=hostRepository.save(new Host("Marko","Markov",c2));

        accommodationController.save(new AccommodationDto("Family", Category.APARTMENT,h1.getId(),4));
        accommodationController.save(new AccommodationDto("Solo", Category.FLAT,h2.getId(),1));



    }
}
