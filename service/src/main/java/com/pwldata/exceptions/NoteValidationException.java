package com.pwldata.exceptions;

public class NoteValidationException extends RuntimeException {

    public NoteValidationException(String message) {
        super(message);
    }

    public NoteValidationException(String message, Throwable e) {
        super(message, e);
    }
}
