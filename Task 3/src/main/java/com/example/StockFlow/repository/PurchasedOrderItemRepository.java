package com.example.StockFlow.repository;

import com.example.StockFlow.entity.PurchasedOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedOrderItemRepository extends JpaRepository<PurchasedOrderItem, String> {
}

