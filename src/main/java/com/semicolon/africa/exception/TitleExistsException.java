package com.semicolon.africa.exception;

public class TitleExistsException extends RuntimeException {
    public TitleExistsException(String message) {
        super(message);
    }
}
