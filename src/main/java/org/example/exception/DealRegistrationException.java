package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DealRegistrationException extends RuntimeException {
    public DealRegistrationException() {
    }

    public DealRegistrationException(String message) {
        super(message);
    }

    public DealRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealRegistrationException(Throwable cause) {
        super(cause);
    }

    public DealRegistrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
