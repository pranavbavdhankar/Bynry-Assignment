package com.example.StockFlow.service;

import com.example.StockFlow.dto.InventoryDTO;
import com.example.StockFlow.dto.UpdateInventoryDTO;
import com.example.StockFlow.entity.Inventory;
import com.example.StockFlow.entity.Product;
import com.example.StockFlow.entity.Warehouse;
import com.example.StockFlow.exception.ResourceNotFoundException;
import com.example.StockFlow.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final WarehouseService warehouseService;
    private final ProductService productService;
    private final InventoryHistoryService inventoryHistoryService;

//    Get Inventory By Id
    public Inventory findById(String id) {
        return inventoryRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Inventory with Id %s not found", id))
                );
    }

//    Add Product to Inventory
    public Inventory addProductToInventory(InventoryDTO inventoryDTO) {

        return inventoryRepository.save(toInventory(inventoryDTO));

    }


//    Update the Inventory
    @Transactional
    public Inventory updateInventory( UpdateInventoryDTO inventoryDTO) {

        Inventory inventory = findById(inventoryDTO.getId());

//       Update the Inventory History
        inventoryHistoryService.add(inventoryDTO, inventory);
        inventory.setUpdatedAt(LocalDateTime.now());
        inventory.setThreshold(inventoryDTO.getThreshold());
        inventory.setQuantity(inventoryDTO.getQuantity());

        return inventoryRepository.save(inventory);

    }

//  Get inventory by warehouse
    public List<Inventory> getAllInventoryByWarehouseId(String warehouseId) {

        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        return inventoryRepository.findAllByWarehouse(warehouse);

    }

//    Convert DTO to Inventory
    public Inventory toInventory(InventoryDTO dto) {
        Warehouse warehouse = warehouseService.getWarehouseById(dto.getWarehouseId());
        Product product = productService.getProductById(dto.getProductId());

        return Inventory
                .builder()
                .id(UUID.randomUUID().toString())
                .product(product)
                .warehouse(warehouse)
                .quantity(dto.getQuantity())
                .threshold(dto.getThreshold())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public InventoryDTO toDTO(Inventory inventory) {
        return InventoryDTO.builder()
                .id(inventory.getId())
                .productId(inventory.getProduct().getId())
                .warehouseId(inventory.getWarehouse().getId())
                .quantity(inventory.getQuantity())
                .threshold(inventory.getThreshold())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }
}
