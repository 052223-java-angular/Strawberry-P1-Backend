package com.revature.strawberry.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.strawberry.dtos.requests.NewRoleRequest;
import com.revature.strawberry.entities.Role;
import com.revature.strawberry.services.RoleService;
import com.revature.strawberry.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody NewRoleRequest req) {
        if (!roleService.isUniqueRole(req.getName())) {
            throw new ResourceConflictException(req.getName() + " role already exists.");
        }

        roleService.createRole(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
