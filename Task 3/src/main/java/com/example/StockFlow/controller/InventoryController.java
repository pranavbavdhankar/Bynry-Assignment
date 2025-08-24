package com.example.StockFlow.controller;


import com.example.StockFlow.dto.*;
import com.example.StockFlow.entity.Inventory;
import com.example.StockFlow.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

//    Add Product to inventory
    @PostMapping
    public ResponseEntity<Response> addProductToInventory(@Valid @RequestBody InventoryDTO inventoryDTO) {

        inventoryService.addProductToInventory(inventoryDTO);
        return new ResponseEntity<>(
                new Response(HttpStatus.CREATED.value(), "Product added to Inventory", LocalDateTime.now()),
                HttpStatus.CREATED
        );

    }

//    Update the inventory
    @PutMapping
    public ResponseEntity<Response> updateInventory(@Valid @RequestBody UpdateInventoryDTO inventoryDTO) {

        inventoryService.updateInventory(inventoryDTO);
        return ResponseEntity.ok(new Response(HttpStatus.OK.value(), "Inventory updated", LocalDateTime.now()));

    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<InventoryDTO>> getAllInventoryByWarehouseId(@PathVariable String warehouseId) {
        List<Inventory> inventory = inventoryService.getAllInventoryByWarehouseId(warehouseId);
        List<InventoryDTO> inventoryDTOList = inventory.stream()
                .map(inventoryService::toDTO)
                .toList();

        return new ResponseEntity<>(inventoryDTOList, HttpStatus.OK);
    }
}
