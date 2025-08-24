package com.example.StockFlow.controller;

import com.example.StockFlow.dto.AddProductDTO;
import com.example.StockFlow.dto.ProductDTO;
import com.example.StockFlow.dto.Response;
import com.example.StockFlow.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    Get all the products
    @GetMapping("company/{companyId}")
    public ResponseEntity<Page<ProductDTO>> getAllProductsByCompany(@PathVariable String companyId, Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProductsByCompanyId(companyId, pageable).map(productService::toDTO));
    }

//    Get Product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(
                productService.toDTO(productService.getProductById(id))
        );
    }

//    Add new product
    @PostMapping
    public ResponseEntity<Response> createProduct(@Valid @RequestBody AddProductDTO dto) {
        productService.createProduct(dto);

        return new ResponseEntity<>(
                new Response(HttpStatus.CREATED.value(), "Product Created Successfully", LocalDateTime.now()),
                HttpStatus.CREATED
        );

    }

}
