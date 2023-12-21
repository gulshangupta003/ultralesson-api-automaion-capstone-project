package utilities.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private int statusCode;

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
