package com.revature.strawberry.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.strawberry.dtos.requests.NewRoleRequest;
import com.revature.strawberry.entities.Role;
import com.revature.strawberry.repositories.RoleRepository;
import com.revature.strawberry.utils.custom_exceptions.ResourceConflictException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role createRole(NewRoleRequest req) {
        Role newRole = new Role(req);
        return roleRepository.save(newRole);
    }

    public boolean isUniqueRole(String name) {
        return roleRepository.findByName(name).isEmpty();
    }
}
