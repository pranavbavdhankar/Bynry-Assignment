package com.example.StockFlow.repository;

import com.example.StockFlow.entity.Company;
import com.example.StockFlow.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
    List<Warehouse> getAllByCompany(Company company);
}

