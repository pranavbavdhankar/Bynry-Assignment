package com.example.StockFlow.controller;

import com.example.StockFlow.dto.Response;
import com.example.StockFlow.dto.WarehouseDTO;
import com.example.StockFlow.entity.Warehouse;
import com.example.StockFlow.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

//    Create new warehouse
    @PostMapping
    public ResponseEntity<Response> createWarehouse(@RequestBody @Valid WarehouseDTO warehouseDTO) {
        return ResponseEntity.ok(warehouseService.createWarehouse(warehouseDTO));
    }

//    Get all warehouse by company id
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Warehouse>> getAllWarehouseByCompanyId(@PathVariable String companyId) {

        List<Warehouse> warehouseList = warehouseService.getAllWarehouseByCompanyId(companyId);
        return ResponseEntity.ok(warehouseList);

    }

}
