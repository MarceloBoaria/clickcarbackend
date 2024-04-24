package com.clickcar.clickcarback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickcar.clickcarback.dtos.cars.CarInput;
import com.clickcar.clickcarback.dtos.cars.CarOutput;
import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.repositories.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    @Transactional
    public CarOutput create(CarInput input) {
        Car car = convertInputToCar(input);
        car = repository.save(car);
        return convertCarToOutput(car);
    }

    public List<CarOutput> list() {
        // [car1, car2, car3].map(car -> convertCarToOutput(car));
        return repository
        .findAll()
        .stream()
        .map(car -> convertCarToOutput(car))
        .toList();
    }

    public CarOutput read(Long id) {
        Car car = repository.findById(id).orElse(null);
        return convertCarToOutput(car);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public CarOutput update(Long id, CarInput input) {
        if(repository.existsById(id)) {
            Car car = convertInputToCar(input);
            car.setId(id);
            car = repository.save(car);
            return convertCarToOutput(car);
        } else {
            return null;
        }
    }

    private Car convertInputToCar(CarInput input) {

        Car car = new Car();

        car.setBrand(car.getBrand());
        car.setModel(car.getModel());
        car.setYearManufacture(car.getYearManufacture());
        car.setMileage(car.getMileage());
        car.setDetails(car.getDetails());
        return car;

    }

    private CarOutput convertCarToOutput(Car car) {

        if (car == null) {
            return null;
        }

        CarOutput output = new CarOutput(
            
            car.getId(),
            car.getBrand(),
            car.getModel(),
            car.getYearManufacture(),
            car.getMileage(),
            car.getDetails());
            return output;

    }
    
}

