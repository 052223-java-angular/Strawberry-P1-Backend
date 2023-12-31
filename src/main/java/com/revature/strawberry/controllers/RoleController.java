package com.revature.strawberry.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.strawberry.dtos.requests.NewRoleRequest;
import com.revature.strawberry.entities.Role;
import com.revature.strawberry.services.JwtService;
import com.revature.strawberry.services.RoleService;
import com.revature.strawberry.utils.custom_exceptions.AdminPermissionException;
import com.revature.strawberry.utils.custom_exceptions.InvalidTokenException;
import com.revature.strawberry.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody NewRoleRequest req, @RequestHeader("auth-token") String token) {
        // get token from header
        if (token == null || token.isEmpty()) {
            throw new InvalidTokenException("No token provided");
        }

        // extract user role from token
        String userRole = jwtService.extractUserRole(token);

        // validate token
        if (userRole == null || userRole.isEmpty()) {
            throw new InvalidTokenException("Invalid token");
        }

        // validate user role
        if (!userRole.equals("ADMIN")) {
            throw new AdminPermissionException("You are not authorized to create a category");
        }

        if (!roleService.isUniqueRole(req.getName())) {
            throw new ResourceConflictException(req.getName() + " role already exists.");
        }

        roleService.createRole(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
