package com.example.StockFlow.service;

import com.example.StockFlow.dto.SupplierDTO;
import com.example.StockFlow.entity.Supplier;
import com.example.StockFlow.exception.ResourceNotFoundException;
import com.example.StockFlow.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier getSupplierById(String id) {
        return supplierRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Supplier with id '%s' not found", id))
                );
    }

    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }


    public Supplier addSupplier(SupplierDTO supplierDTO) {
        return supplierRepository.save( toSupplier(supplierDTO) );
    }

    public SupplierDTO toDTO(Supplier supplier) {
        return SupplierDTO.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .address(supplier.getAddress())
                .contactNo(supplier.getContactNo())
                .registeredEmail(supplier.getRegisteredEmail())
                .build();
    }


    public Supplier toSupplier(SupplierDTO supplier) {
        return Supplier.builder()
                .id(UUID.randomUUID().toString())
                .name(supplier.getName())
                .address(supplier.getAddress())
                .contactNo(supplier.getContactNo())
                .registeredEmail(supplier.getRegisteredEmail())
                .build();
    }
}
