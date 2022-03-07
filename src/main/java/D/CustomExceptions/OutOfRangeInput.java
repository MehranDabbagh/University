package CustomExceptions;

public class OutOfRangeInput extends RuntimeException {
    public OutOfRangeInput() {
    }

    public OutOfRangeInput(String message) {
        super(message);
    }

    public OutOfRangeInput(String message, Throwable cause) {
        super(message, cause);
    }
}
