package switchfully.com.eurder.orders;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import switchfully.com.eurder.exceptions.CustomerNotFoundException;
import switchfully.com.eurder.exceptions.ItemNotFoundException;
import switchfully.com.eurder.exceptions.UnauthorizedException;
import switchfully.com.eurder.exceptions.WrongPasswordException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = OrderController.class)
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Following validation error(s) occurred while creating a order");

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            this.logger.error(fieldName + " : " + errorMessage);
            errors.put(fieldName, errorMessage);
        });
        response.put("errors", errors);
        this.logger.error("Following validation error(s) occurred while creating a order");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void customerNotFoundException(CustomerNotFoundException exception, HttpServletResponse response) throws IOException {
        this.logger.error(exception.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void itemNotFoundException(ItemNotFoundException exception, HttpServletResponse response) throws IOException {
        this.logger.error(exception.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void UnauthorizedException(UnauthorizedException exception, HttpServletResponse response) throws IOException {
        this.logger.error(exception.getMessage());
        response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void WrongPasswordException(WrongPasswordException exception, HttpServletResponse response) throws IOException {
        this.logger.error(exception.getMessage());
        response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
    }
}
