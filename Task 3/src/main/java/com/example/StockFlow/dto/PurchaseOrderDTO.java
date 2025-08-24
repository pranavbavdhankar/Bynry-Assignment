package com.example.StockFlow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PurchaseOrderDTO {

    private String id;

    @NotBlank
    private String supplierId;

    @NotBlank
    private String companyId;

    @NotBlank
    private String status; // Use enum if desired

    private String purchaseDate;
    private String updatedAt;

}

