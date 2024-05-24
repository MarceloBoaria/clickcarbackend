package com.clickcar.clickcarback.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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

    public List<CarOutput> list(Pageable page, Car carExample) {
        ExampleMatcher matcher = ExampleMatcher
        .matchingAny()
        .withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING);

        Example<Car> example = Example.of(carExample, matcher);

        return repository
        .findAll(example, page)
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

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(input, Car.class);

    }

    public CarOutput convertCarToOutput(Car car) {

        if (car == null) {
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        var carMapped = modelMapper.map(car, CarOutput.class);
        carMapped.setFavoritsNumber(car.getUsersFavorits().size());
        return carMapped;

    }
    
}

