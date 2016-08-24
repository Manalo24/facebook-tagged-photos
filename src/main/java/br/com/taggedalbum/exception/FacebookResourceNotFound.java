package br.com.taggedalbum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by rafaelperetta on 8/23/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FacebookResourceNotFound extends Exception {

    public FacebookResourceNotFound(String message) {
        super(message);
    }

    public FacebookResourceNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
