package com.example.StockFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InventoryDTO {

    private String id;

    @NotBlank
    private String productId;

    @NotBlank
    private String warehouseId;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Integer threshold;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

