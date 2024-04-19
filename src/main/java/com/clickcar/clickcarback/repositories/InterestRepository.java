package com.clickcar.clickcarback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Interest;

import jakarta.persistence.Embeddable;

public interface InterestRepository extends JpaRepository<Interest, Embeddable> {
    
}
