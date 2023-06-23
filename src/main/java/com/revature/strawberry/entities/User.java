package com.revature.strawberry.entities;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.strawberry.dtos.requests.NewRegisterRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Cart cart;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Order> orders;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;

    public User(NewRegisterRequest req, Role role) {
        this.id = UUID.randomUUID().toString();
        this.username = req.getUsername();
        this.email = req.getEmail();
        this.password = req.getPassword();
        this.role = role;
    }
}
