package com.revature.strawberry.entities;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "total", nullable = false)
    private double total;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
