package com.example.StockFlow.service;

import com.example.StockFlow.entity.Product;
import com.example.StockFlow.entity.ProductSupplier;
import com.example.StockFlow.entity.Supplier;
import com.example.StockFlow.repository.ProductSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductSupplierService {

    private final ProductSupplierRepository productSupplierRepository;

    public void addProductSupplier(Product product, Supplier supplier) {

        ProductSupplier productSupplier = ProductSupplier.builder()
                .id(UUID.randomUUID().toString())
                .supplier(supplier)
                .product(product)
                .createdAt(LocalDateTime.now())
                .build();

        productSupplierRepository.save(productSupplier);
    }

    public List<ProductSupplier> findByProductId(Product p) {
        return productSupplierRepository.findAllByProduct(p);
    }

}
