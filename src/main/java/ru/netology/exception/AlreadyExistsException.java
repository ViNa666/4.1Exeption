package ru.netology.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String s) {
        super(s);
    }
}
