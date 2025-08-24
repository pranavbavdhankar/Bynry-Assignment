package com.example.StockFlow.repository;

import com.example.StockFlow.entity.Product;
import com.example.StockFlow.entity.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, String> {
    List<ProductSupplier> findAllByProduct(Product p);
}

