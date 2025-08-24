package com.example.StockFlow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "purchased_order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchasedOrderItem {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private PurchaseOrder order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "is_bundled", nullable = false)
    private Boolean isBundled = false;

    @Column(name = "price_per_unit", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerUnit;

    @Column(name = "received_quantity")
    private Integer receivedQuantity;
}

