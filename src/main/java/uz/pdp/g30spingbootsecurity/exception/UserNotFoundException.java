package uz.pdp.g30spingbootsecurity.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException byId(Long id){
        return new UserNotFoundException("User not found with id - " + id);
    }
}
