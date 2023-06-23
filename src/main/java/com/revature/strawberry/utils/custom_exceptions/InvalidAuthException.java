package com.revature.strawberry.utils.custom_exceptions;

public class InvalidAuthException extends RuntimeException {
    public InvalidAuthException(String message) {
        super(message);
    }
}
