package mk.ukim.finki.lab.config.init;

import mk.ukim.finki.lab.model.domain.*;
import mk.ukim.finki.lab.repository.AccommodationRepository;
import mk.ukim.finki.lab.repository.CountryRepository;
import mk.ukim.finki.lab.repository.HostRepository;
import mk.ukim.finki.lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
//   @PostConstruct
    public void init(){
        Country c1=countryRepository.save(new Country("Macedonia","Europe"));
        Country c2=countryRepository.save(new Country("France","Europe"));

        Host h1=hostRepository.save(new Host("Host1","Host1",c1));
        Host h2=hostRepository.save(new Host("Host2","Host2",c2));

        accommodationRepository.save(new Accommodation("Family", Category.APARTMENT,h1,4));
        accommodationRepository.save(new Accommodation("Solo", Category.FLAT,h2,1));
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
