package mk.ukim.finki.lab.model.exceptions;

public class HostException extends Exception{
    public HostException(Long id) {
        super("host with id "+ id +" was not found");
    }
}
