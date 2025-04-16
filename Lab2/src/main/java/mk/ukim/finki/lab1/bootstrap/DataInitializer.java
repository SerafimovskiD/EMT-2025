package mk.ukim.finki.lab1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.model.domain.Host;
import mk.ukim.finki.lab1.model.domain.Role;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.repository.CountryRepository;
import mk.ukim.finki.lab1.repository.HostRepository;
import mk.ukim.finki.lab1.repository.UserRepository;
import mk.ukim.finki.lab1.web.AccommodationController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationController accommodationController;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(AccommodationController accommodationController, CountryRepository countryRepository, HostRepository hostRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationController = accommodationController;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
   @PostConstruct
    public void init(){
        countryRepository.save(new Country("Macedonia","Europe"));
        countryRepository.save(new Country("France","Europe"));

//        Host h1=hostRepository.save(new Host("Damjan","Serafimovski",c1));
//        Host h2=hostRepository.save(new Host("Marko","Markov",c2));

//        accommodationController.save(new Accommodation("Family", Category.APARTMENT,h1,4));
//        accommodationController.save(new Accommodation("Solo", Category.FLAT,h2,1));
       userRepository.save(new User(
               "nv",
               passwordEncoder.encode("nv"),
               "Naum",
               "Vlavceski",
               Role.ROLE_HOST
       ));
       userRepository.save(new User(
               "ds",
               passwordEncoder.encode("ds"),
               "Dame",
               "SERAFIM",
               Role.ROLE_USER
       ));
   }
}
