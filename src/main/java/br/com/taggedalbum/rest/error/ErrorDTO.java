package br.com.taggedalbum.rest.error;

/**
 * Created by rafaelperetta on 8/23/16.
 */
public class ErrorDTO {

    private String code;

    private String message;

    public ErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
