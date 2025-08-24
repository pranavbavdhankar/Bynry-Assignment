package com.example.StockFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PurchasedOrderItemDTO {

    private String id;

    @NotBlank
    private String orderId;

    @NotBlank
    private String productId;

    @NotBlank
    private String warehouseId;

    @NotNull
    @Positive
    private Integer quantity;

    private Boolean isBundled = false;

    @NotNull
    @Positive
    private Double pricePerUnit;

    private Integer receivedQuantity;

}
