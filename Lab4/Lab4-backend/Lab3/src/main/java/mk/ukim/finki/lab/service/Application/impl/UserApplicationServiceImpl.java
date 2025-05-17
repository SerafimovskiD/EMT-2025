package mk.ukim.finki.lab.service.Application.impl;

import mk.ukim.finki.lab.dto.LoginResponseDto;
import mk.ukim.finki.lab.dto.LoginUserDto;
import mk.ukim.finki.lab.dto.CreateUserDto;
import mk.ukim.finki.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab.dto.DisplayUserDto;
import mk.ukim.finki.lab.model.domain.Accommodation;
import mk.ukim.finki.lab.model.domain.User;
import mk.ukim.finki.lab.helpers.JwtHelper;
import mk.ukim.finki.lab.service.Application.UserApplicationService;
import mk.ukim.finki.lab.service.Domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }
    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<DisplayAccommodationDto> addAccommodationToTempList(String username, Long accommodationId) {
        User user = userService.findByUsername(username);
        userService.addAccommodationToTempList(username, accommodationId);
        List<Accommodation> accommodationList = user.getTemporaryReservations();
        return accommodationList.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    @Override
    public List<DisplayAccommodationDto> getUserTempList(String username) {
        return userService.getUserTempList(username)
                .stream()
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DisplayAccommodationDto> bookAnAccommodationFromTempList(String username) {
        return userService.bookAnAccommodationFromTempList(username).stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());    }
}