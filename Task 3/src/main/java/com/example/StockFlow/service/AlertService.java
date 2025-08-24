package com.example.StockFlow.service;

import com.example.StockFlow.dto.AlertDTO;
import com.example.StockFlow.dto.SupplierDTO;
import com.example.StockFlow.entity.*;
import com.example.StockFlow.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final ProductSupplierService productSupplierService;

    List<Alert> findAllByCompanyId(String companyId){
        return alertRepository.findAllByCompanyId(companyId);
    }

    public AlertDTO toDTO(Alert alert) {

        Inventory inventory = alert.getInventory();
        Product product = inventory.getProduct();
        Company company = product.getCompany();
        Warehouse warehouse = inventory.getWarehouse();

        // get all suppliers for product
        List<ProductSupplier> productSuppliers = productSupplierService.findByProductId(product);

        List<SupplierDTO> suppliers = productSuppliers.stream()
                .map(ps -> {
                    Supplier s = ps.getSupplier();
                    return SupplierDTO.builder()
                            .id(s.getId())
                            .name(s.getName())
                            .registeredEmail(s.getRegisteredEmail())
                            .contactNo(s.getContactNo())
                            .build();
                }).toList();

        return AlertDTO.builder()
                .id(alert.getId())
                .currentQuantity(alert.getCurrentQuantity())
                .threshold(alert.getThreshold())
                .isProcessed(alert.getIsProcessed())
                .createdAt(alert.getCreatedAt())
                .productId(product.getId())
                .productName(product.getName())
                .companyId(company.getId())
                .companyName(company.getName())
                .warehouseId(warehouse.getId())
                .warehouseName(warehouse.getName())
                .suppliers(suppliers)
                .build();
    }


}
