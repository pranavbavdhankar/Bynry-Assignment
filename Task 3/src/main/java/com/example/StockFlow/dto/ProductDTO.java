package com.example.StockFlow.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id;

    @NotBlank
    private String companyId;

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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

