package com.revature.strawberry.utils.custom_exceptions;

public class AdminPermissionException extends RuntimeException {
    public AdminPermissionException(String message) {
        super(message);
    }
}