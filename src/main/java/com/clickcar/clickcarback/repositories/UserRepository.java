package com.clickcar.clickcarback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<Car> findByNameAndCpf(String name, String cpf);
}
