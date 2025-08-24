package com.example.StockFlow.repository;

import com.example.StockFlow.entity.InventoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, String> {
}

