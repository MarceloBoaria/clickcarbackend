package com.clickcar.clickcarback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandAndModel(String brand, String model);
}
