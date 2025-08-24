package com.example.StockFlow.repository;

import com.example.StockFlow.entity.Company;
import com.example.StockFlow.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findBySkuAndCompanyId(String sku, String companyId);

    boolean existsBySkuAndCompany(String sku, Company company);

    Page<Product> findAllByCompany(Company company, Pageable pageable);
}

