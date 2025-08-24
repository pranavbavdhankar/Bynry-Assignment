package com.example.StockFlow.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateInventoryDTO {

    private String id;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Integer threshold;

    private String changeType;
    private String note;

}
