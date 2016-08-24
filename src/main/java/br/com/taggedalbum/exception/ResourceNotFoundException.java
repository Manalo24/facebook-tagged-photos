package br.com.taggedalbum.exception;

/**
 * Created by rafaelperetta on 20/07/16.
 */

public class ResourceNotFoundException extends RuntimeException {

    private Long id;

    public ResourceNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
