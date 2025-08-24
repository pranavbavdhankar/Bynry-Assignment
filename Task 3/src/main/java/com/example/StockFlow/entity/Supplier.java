package com.example.StockFlow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(name = "contact_no", nullable = false, length = 15)
    private String contactNo;

    @Column(name = "registered_email", nullable = false, length = 100)
    private String registeredEmail;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSupplier> productSuppliers = new ArrayList<>();
}
