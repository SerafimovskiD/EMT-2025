package mk.ukim.finki.lab1.model.exceptions;

public class CountryException extends Exception{
    public CountryException(Long id) {
        super("Country wtih id " + id + " was not found");
    }
}
