package com.revature.strawberry.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.revature.strawberry.dtos.requests.NewLoginRequest;
import com.revature.strawberry.dtos.requests.NewRegisterRequest;
import com.revature.strawberry.entities.Role;
import com.revature.strawberry.entities.User;
import com.revature.strawberry.repositories.UserRepository;
import com.revature.strawberry.utils.custom_exceptions.InvalidAuthException;
import com.revature.strawberry.utils.custom_exceptions.UserNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    // TODO: Implement login method
    public User login(NewLoginRequest req) {
        // find user by username
        User foundUser = findByUsername(req.getUsername());

        // check if password matches
        if (BCrypt.checkpw(req.getPassword(), foundUser.getPassword())) {
            return foundUser;
        }

        throw new InvalidAuthException("Invalid credentials");
    }

    public User register(NewRegisterRequest req) {
        // find role customer
        Role foundRole = roleService.findByName("CUSTOMER");

        // hash password
        String hashed = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());
        req.setPassword(hashed);

        // create new user
        User newUser = new User(req, foundRole);

        // save new user
        return userRepository.save(newUser);
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isUniqueUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean isUniqueEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public User findByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            return userOpt.get();
        }

        throw new UserNotFoundException("User not found");
    }
}
