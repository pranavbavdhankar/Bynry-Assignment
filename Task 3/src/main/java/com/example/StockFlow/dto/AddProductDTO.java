package com.example.StockFlow.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AddProductDTO {

    @NotBlank
    private String companyId;

    @NotEmpty
    private List<String> supplierId;

    @NotBlank
    @Size(min = 1, max = 100)
    private String sku;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    private String description;

}

