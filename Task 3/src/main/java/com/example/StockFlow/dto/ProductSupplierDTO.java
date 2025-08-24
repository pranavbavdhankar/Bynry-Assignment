package com.example.StockFlow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSupplierDTO {

    @NotBlank
    private String productId;

    @NotBlank
    private String supplierId;

}

