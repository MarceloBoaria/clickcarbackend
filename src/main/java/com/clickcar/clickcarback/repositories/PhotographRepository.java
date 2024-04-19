package com.clickcar.clickcarback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Photograph;

public interface PhotographRepository extends JpaRepository<Photograph, Long> {
    
}
