package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DealPriceChangedException extends RuntimeException {
    public DealPriceChangedException() {
    }

    public DealPriceChangedException(String message) {
        super(message);
    }

    public DealPriceChangedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealPriceChangedException(Throwable cause) {
        super(cause);
    }

    public DealPriceChangedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
