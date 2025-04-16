package mk.ukim.finki.lab1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TempReservationNotFoundException extends RuntimeException {

    public TempReservationNotFoundException(Long id) {
        super(String.format("Shopping cart with id: %d was not found", id));
    }
}

