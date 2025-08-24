package com.example.StockFlow.repository;

import com.example.StockFlow.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, String> {

    @Query("""
        SELECT a FROM Alert a
        JOIN a.inventory i
        JOIN i.product p
        JOIN p.company c
        WHERE c.id = :companyId
    """)
    List<Alert> findAllByCompanyId(@Param("companyId") String companyId);

}

