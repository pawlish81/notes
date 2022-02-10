package com.pwldata.exceptions;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(String message) {
        super(message);
    }

    public NoteNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}
