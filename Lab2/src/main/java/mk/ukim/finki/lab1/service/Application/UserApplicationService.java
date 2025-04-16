package mk.ukim.finki.lab1.service.Application;


import mk.ukim.finki.lab1.dto.CreateUserDto;
import mk.ukim.finki.lab1.dto.DisplayUserDto;
import mk.ukim.finki.lab1.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}


