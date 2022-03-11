package com.pwldata.exceptions;

public class RocketValidationException extends RuntimeException {

    public RocketValidationException(String message) {
        super(message);
    }

    public RocketValidationException(String message, Throwable e) {
        super(message, e);
    }
}
