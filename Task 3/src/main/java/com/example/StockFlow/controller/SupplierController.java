package com.example.StockFlow.controller;

import com.example.StockFlow.dto.Response;
import com.example.StockFlow.dto.SupplierDTO;
import com.example.StockFlow.entity.Supplier;
import com.example.StockFlow.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

//    Get all supplier
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {

        List<Supplier> suppliers = supplierService.getAllSupplier();
        List<SupplierDTO> supplierDTOS =  suppliers.stream()
                .map(supplierService::toDTO)
                .toList();

        return ResponseEntity.ok(supplierDTOS);

    }

//    Get Supplier by id
    @GetMapping("{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable String id) {

        Supplier supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok( supplierService.toDTO(supplier) );

    }

//    Add new Supplier
    @PostMapping
    public ResponseEntity<Response> addSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        supplierService.addSupplier(supplierDTO);

        return new ResponseEntity<>(
                new Response(HttpStatus.CREATED.value(), "Supplier Added Successfully", LocalDateTime.now()),
                HttpStatus.CREATED
        );
    }

}
