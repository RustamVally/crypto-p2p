package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DealBtcNotEnoughException extends RuntimeException {
    public DealBtcNotEnoughException() {
    }

    public DealBtcNotEnoughException(String message) {
        super(message);
    }

    public DealBtcNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealBtcNotEnoughException(Throwable cause) {
        super(cause);
    }

    public DealBtcNotEnoughException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
