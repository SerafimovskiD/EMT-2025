package mk.ukim.finki.lab.service.Domain;

import mk.ukim.finki.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab.dto.DisplayUserDto;
import mk.ukim.finki.lab.model.domain.Accommodation;
import mk.ukim.finki.lab.model.domain.User;
import mk.ukim.finki.lab.model.domain.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);
    User findByUsername(String username);

    List<Accommodation> addAccommodationToTempList(String username, Long accommodationId);
    List<Accommodation> getUserTempList(String username);
    List<Accommodation> bookAnAccommodationFromTempList(String username);
}


