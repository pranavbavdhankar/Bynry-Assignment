package com.example.StockFlow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertDTO {

    private String id;

    // Product details
    private String productId;
    private String productName;

    // Company details
    private String companyId;
    private String companyName;

    // Warehouse details
    private String warehouseId;
    private String warehouseName;

    // Alert info
    private Integer currentQuantity;
    private Integer threshold;
    private Boolean isProcessed;
    private LocalDateTime createdAt;

    private List<SupplierDTO> suppliers;
}
