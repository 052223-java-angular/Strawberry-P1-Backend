package com.revature.strawberry.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.strawberry.utils.custom_exceptions.InvalidAuthException;
import com.revature.strawberry.utils.custom_exceptions.ResourceConflictException;
import com.revature.strawberry.utils.custom_exceptions.RoleNotFoundException;
import com.revature.strawberry.utils.custom_exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflict(ResourceConflictException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("timestamp", new Date(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleRoleNotFound(RoleNotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("timestamp", new Date(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(InvalidAuthException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidAuth(InvalidAuthException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("timestamp", new Date(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("timestamp", new Date(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}