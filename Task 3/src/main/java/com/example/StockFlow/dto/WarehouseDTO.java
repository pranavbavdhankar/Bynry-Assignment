package com.example.StockFlow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String companyId;

}

