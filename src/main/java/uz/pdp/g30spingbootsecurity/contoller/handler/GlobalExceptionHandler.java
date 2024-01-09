package uz.pdp.g30spingbootsecurity.contoller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class, RuntimeException.class})
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ue, RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(e.getMessage());
    }
}
