package com.example.ecom_backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name required")
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)  // edger load data when program start
    @JoinColumn(name = "user_id")
    private Users user;
}
