package br.com.taggedalbum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by rafaelperetta on 20/07/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The resource requested was not found.")
public class ResourceNotFoundException extends Exception {
}
