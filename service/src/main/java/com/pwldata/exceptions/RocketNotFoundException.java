package com.pwldata.exceptions;

public class RocketNotFoundException extends RuntimeException {

    public RocketNotFoundException(String message) {
        super(message);
    }

    public RocketNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}
