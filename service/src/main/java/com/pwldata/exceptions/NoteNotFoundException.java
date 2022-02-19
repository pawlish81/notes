package com.pwldata.exceptions;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(String message) {
        super(message);

    }

    public NoteNotFoundException(String message, Object... params) {
        super(String.format(message, params));

    }

    public NoteNotFoundException(Throwable cause, String message, Object... params) {
        super(String.format(message, params), cause);
    }
}
