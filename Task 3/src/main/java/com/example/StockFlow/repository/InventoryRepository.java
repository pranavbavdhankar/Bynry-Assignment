package com.example.StockFlow.repository;

import com.example.StockFlow.entity.Inventory;
import com.example.StockFlow.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findAllByWarehouse(Warehouse warehouse);
}
