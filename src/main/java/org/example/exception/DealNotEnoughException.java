package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DealNotEnoughException extends RuntimeException {
    public DealNotEnoughException() {
    }

    public DealNotEnoughException(String message) {
        super(message);
    }

    public DealNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealNotEnoughException(Throwable cause) {
        super(cause);
    }

    public DealNotEnoughException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
