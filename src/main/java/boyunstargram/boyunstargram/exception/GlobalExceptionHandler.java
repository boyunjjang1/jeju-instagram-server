package boyunstargram.boyunstargram.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("handleResourceNotFoundException", e);
        final ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("handleMissingServletRequestParameterException", e);
        final ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException e) {
        log.error("handleAuthenticationException", e);
        final ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        final ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<ExceptionResponse> handleForbiddenException(ForbiddenException e) {
        log.error("handleForbiddenException", e);
        final ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ParameterNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleParameterNotFoundException(ParameterNotFoundException e) {
        log.error("handleParameterNotFoundException", e);
        final ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceConflictException.class)
    protected ResponseEntity<ExceptionResponse> handleResourceConflictException(ResourceConflictException e) {
        log.error("handleResourceConflictException", e);
        final ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
