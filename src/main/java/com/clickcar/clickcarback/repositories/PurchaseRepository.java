package com.clickcar.clickcarback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    
}
