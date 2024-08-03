package com.semicolon.africa.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(String noteNotFound) {
        super(noteNotFound);
    }
}
