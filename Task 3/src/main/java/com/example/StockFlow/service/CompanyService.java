package com.example.StockFlow.service;

import com.example.StockFlow.dto.AlertDTO;
import com.example.StockFlow.dto.CompanyDTO;
import com.example.StockFlow.dto.ProductDTO;
import com.example.StockFlow.dto.SupplierDTO;
import com.example.StockFlow.entity.*;
import com.example.StockFlow.exception.ResourceNotFoundException;
import com.example.StockFlow.repository.AlertRepository;
import com.example.StockFlow.repository.CompanyRepository;
import com.example.StockFlow.repository.ProductSupplierRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AlertService alertService;

//    Get company By id
    public Company findById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Company with id '%s' not found", id))
        );
    }

//    Get all Companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

//    Create the new Company
    public Company createCompany(CompanyDTO companyDTO) {
        Company company = toCompany(companyDTO);
        return companyRepository.save(company);
    }

    //    Get Low Stock Alerts
    public List<AlertDTO> getLowStockAlerts(String companyId) {
        this.findById(companyId);

        List<Alert> alerts = alertService.findAllByCompanyId(companyId);
        List<AlertDTO> response = new ArrayList<>();
        for(Alert alert : alerts) {
            response.add(alertService.toDTO(alert));
        }
        return response;

    }

    // Convert company to DTO
    public CompanyDTO toDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .contactNo(company.getContactNo())
                .registeredEmail(company.getRegisteredEmail())
                .build();
    }

    // Convert DTO to company
    public Company toCompany(CompanyDTO companyDTO) {
        return Company.builder()
                .id(UUID.randomUUID().toString())
                .name(companyDTO.getName())
                .address(companyDTO.getAddress())
                .contactNo(companyDTO.getContactNo())
                .registeredEmail(companyDTO.getRegisteredEmail())
                .build();

    }

}
