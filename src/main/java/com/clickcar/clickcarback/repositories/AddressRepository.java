package com.clickcar.clickcarback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
