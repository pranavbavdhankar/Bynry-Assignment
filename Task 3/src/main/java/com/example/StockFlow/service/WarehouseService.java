package com.example.StockFlow.service;

import com.example.StockFlow.dto.Response;
import com.example.StockFlow.dto.WarehouseDTO;
import com.example.StockFlow.entity.Company;
import com.example.StockFlow.entity.Warehouse;
import com.example.StockFlow.exception.ResourceNotFoundException;
import com.example.StockFlow.repository.WarehouseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final CompanyService companyService;

    public Response createWarehouse(@Valid WarehouseDTO warehouseDTO) {
        Warehouse warehouse = toWarehouse(warehouseDTO);
        this.warehouseRepository.save(warehouse);
        return new Response(HttpStatus.CREATED.value(), "Warehouse created Successfully", LocalDateTime.now());
    }

    public Warehouse getWarehouseById(String id) {
        return this.warehouseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Warehouse with id '%s' not found", id)));
    }

    public Warehouse toWarehouse(WarehouseDTO dto) {
        Company company = companyService.findById(dto.getCompanyId());
        return Warehouse
                .builder()
                .id(UUID.randomUUID().toString())
                .name(dto.getName())
                .company(company)
                .build();
    }

    public List<Warehouse> getAllWarehouseByCompanyId(String companyId) {
        Company company = companyService.findById(companyId);
        return warehouseRepository.getAllByCompany(company);
    }
}
