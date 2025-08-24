package com.example.StockFlow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventory_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryHistory {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @Column(name = "change_type", nullable = false)
    private String changeType;

    @Column(name = "quantity_changed", nullable = false)
    private Integer quantityChanged;

    @Column(name = "previous_quantity", nullable = false)
    private Integer previousQuantity;

    @Column(name = "new_quantity", nullable = false)
    private Integer newQuantity;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}

