package com.example.StockFlow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @Column(name = "current_quantity", nullable = false)
    private Integer currentQuantity;

    @Column(name = "threshold", nullable = false)
    private Integer threshold;

    @Column(name = "is_processed", nullable = false)
    private Boolean isProcessed = false;  // default false

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

