package com.clickcar.clickcarback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
}
