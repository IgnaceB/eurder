package switchfully.com.eurder.security;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
