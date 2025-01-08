package master_details_service.exception;

public class NumberAlreadyExistsException extends RuntimeException {
    public NumberAlreadyExistsException(String message) {
        super(message);
    }
}