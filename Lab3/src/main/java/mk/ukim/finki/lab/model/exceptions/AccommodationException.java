package mk.ukim.finki.lab.model.exceptions;

public class AccommodationException extends Exception{
    public AccommodationException(Long id) {
        super("Accommodation with id " +id + "  was not found ");
    }
}
