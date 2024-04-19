package com.clickcar.clickcarback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Details;

import jakarta.persistence.Embeddable;

public interface DetailsRepository extends JpaRepository<Details, Embeddable> {
    
}
