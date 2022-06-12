package ru.tsvlad.user.restapi.controller.advice.exceptions;

public class ConflictException extends RuntimeException{
    public ConflictException() {
    }

    public ConflictException(String message) {
        super(message);
    }
}
