package com.example.StockFlow.service;

import com.example.StockFlow.dto.InventoryDTO;
import com.example.StockFlow.dto.UpdateInventoryDTO;
import com.example.StockFlow.entity.Inventory;
import com.example.StockFlow.entity.InventoryHistory;
import com.example.StockFlow.repository.InventoryHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryHistoryService {

    private final InventoryHistoryRepository inventoryHistoryRepository;

    public void add(UpdateInventoryDTO inventoryDTO, Inventory inventory) {
        InventoryHistory inventoryHistory = InventoryHistory
                .builder()
                .id(UUID.randomUUID().toString())
                .inventory(inventory)
                .changeType(inventoryDTO.getChangeType())
                .quantityChanged( inventoryDTO.getQuantity() - inventory.getQuantity())
                .previousQuantity(inventory.getQuantity())
                .newQuantity(inventoryDTO.getQuantity())
                .note(inventoryDTO.getNote())
                .createdAt(LocalDateTime.now())
                .build();

        inventoryHistoryRepository.save(inventoryHistory);
    }

}
