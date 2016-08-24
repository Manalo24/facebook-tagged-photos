package br.com.taggedalbum.rest.error;

import br.com.taggedalbum.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

/**
 * This class handles the exceptions thrown by our REST API.
 */

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorDTO handleUserNotFound(ResourceNotFoundException ex, Locale locale) {
        return new ErrorDTO(HttpStatus.NOT_FOUND.name(), ex.getMessage());
    }

}
