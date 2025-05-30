package mk.ukim.finki.lab.dto;

import mk.ukim.finki.lab.model.domain.User;
import mk.ukim.finki.lab.model.domain.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    /*
        todo: add repeat password logic
     */
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}

