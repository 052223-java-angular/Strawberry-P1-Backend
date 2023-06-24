package com.revature.strawberry.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.strawberry.dtos.requests.NewLoginRequest;
import com.revature.strawberry.dtos.requests.NewRegisterRequest;
import com.revature.strawberry.dtos.responses.Principal;
import com.revature.strawberry.services.JwtService;
import com.revature.strawberry.services.UserService;
import com.revature.strawberry.utils.custom_exceptions.InvalidAuthException;
import com.revature.strawberry.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NewRegisterRequest req) {
        if (!userService.isValidUsername(req.getUsername())) {
            throw new InvalidAuthException(
                    "Invalid username. Minimum eight characters, at least one letter and one number");
        }

        if (!userService.isUniqueUsername(req.getUsername())) {
            throw new ResourceConflictException("Username already exists");
        }

        if (!userService.isValidEmail(req.getEmail())) {
            throw new InvalidAuthException("Invalid email");
        }

        if (!userService.isUniqueEmail(req.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        if (!userService.isValidPassword(req.getPassword())) {
            throw new InvalidAuthException(
                    "Invalid Password. Minimum eight characters, at least one letter and one number");
        }

        if (!userService.isSamePassword(req.getPassword(), req.getConfirmPassword())) {
            throw new InvalidAuthException("Passwords do not match");
        }

        userService.register(req);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody NewLoginRequest req) {
        // get the principal (user)
        Principal principal = userService.login(req);

        // generate a token
        String token = jwtService.generateToken(principal);

        // set the token on the principal
        principal.setToken(token);

        // return the principal
        return ResponseEntity.status(HttpStatus.OK).body(principal);
    }
}
