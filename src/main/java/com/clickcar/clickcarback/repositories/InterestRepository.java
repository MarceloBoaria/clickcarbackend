package com.clickcar.clickcarback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Interest;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    
}
