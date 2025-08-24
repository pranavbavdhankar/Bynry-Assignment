package com.example.StockFlow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(name = "contact_no", nullable = false, length = 15)
    private String contactNo;

    @Column(name = "registered_email", nullable = false, length = 50)
    private String registeredEmail;
}
