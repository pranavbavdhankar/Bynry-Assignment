package com.example.StockFlow.controller;

import com.example.StockFlow.dto.AlertDTO;
import com.example.StockFlow.dto.CompanyDTO;
import com.example.StockFlow.dto.Response;
import com.example.StockFlow.entity.Company;
import com.example.StockFlow.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    // Get all the companies
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {

        List<Company> companies = companyService.getAllCompanies();
        List<CompanyDTO> companiesDTO = companies.stream()
                .map(companyService::toDTO)
                .toList();
        return ResponseEntity.ok(companiesDTO);

    }

//    Get Company by id
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable String id) {

        Company company = companyService.findById(id);
        CompanyDTO companyDTO = companyService.toDTO(company);
        return ResponseEntity.ok(companyDTO);

    }

//    Create new Company
    @PostMapping
    public ResponseEntity<Response> createCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.createCompany(companyDTO);
        return new ResponseEntity<>(
                new Response(HttpStatus.CREATED.value(), "Company Added Successfully", LocalDateTime.now()),
                HttpStatus.CREATED
        );
    }

//    Get the low-stock alert by company-id
    @GetMapping("/{companyId}/alerts/low-stock")
    public ResponseEntity<List<AlertDTO>> lowStockAlerts(@PathVariable String companyId) {
        return ResponseEntity.ok(companyService.getLowStockAlerts(companyId));
    }

}
