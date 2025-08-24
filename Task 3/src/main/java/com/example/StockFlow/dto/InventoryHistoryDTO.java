package com.example.StockFlow.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class InventoryHistoryDTO {

    private String id;

    @NotBlank
    private String inventoryId;

    @NotNull
    private String changeType;

    @NotNull
    @Positive
    private Integer quantityChanged;

    @NotNull
    @Positive
    private Integer previousQuantity;

    @NotNull
    @Positive
    private Integer newQuantity;

    private String note;
    private String createdAt;

}

