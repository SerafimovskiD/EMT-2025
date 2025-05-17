package mk.ukim.finki.lab.service.Application;



import mk.ukim.finki.lab.dto.LoginResponseDto;
import mk.ukim.finki.lab.dto.LoginUserDto;
import mk.ukim.finki.lab.dto.CreateUserDto;
import mk.ukim.finki.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab.dto.DisplayUserDto;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
    List<DisplayAccommodationDto> addAccommodationToTempList(String username, Long accommodationId);
    List<DisplayAccommodationDto> getUserTempList(String username);
    List<DisplayAccommodationDto> bookAnAccommodationFromTempList(String username);
}
