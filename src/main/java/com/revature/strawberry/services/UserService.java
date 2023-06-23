package com.revature.strawberry.services;

import org.springframework.stereotype.Service;

import com.revature.strawberry.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
