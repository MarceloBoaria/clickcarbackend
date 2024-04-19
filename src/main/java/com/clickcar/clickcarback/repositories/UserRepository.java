package com.clickcar.clickcarback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
