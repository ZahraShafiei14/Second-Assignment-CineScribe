package exceptions;

public class ActorsNotFoundException extends RuntimeException {
    String message;

    public ActorsNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}