package com.clickcar.clickcarback.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickcar.clickcarback.dtos.purchases.PurchaseInput;
import com.clickcar.clickcarback.dtos.purchases.PurchaseOutput;
import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.entities.Purchase;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.entities.enums.Category;
import com.clickcar.clickcarback.repositories.CarRepository;
import com.clickcar.clickcarback.repositories.PurchaseRepository;
import com.clickcar.clickcarback.repositories.UserRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    @Transactional
    public PurchaseOutput create(PurchaseInput input) {

        Long userId = input.getUserId();
        Long carId = input.getCarId();
        
        if (userRepository.existsById(userId) && carRepository.existsById(carId)) {
            Purchase purchase = convertInputToPurchase(input);
            purchase = repository.save(purchase);
            return convertPurchaseToOutput(purchase);

        } else {
            return null;
        }
    }

    public List<PurchaseOutput> list(Pageable page, Purchase purchaseExample) {
        ExampleMatcher matcher = ExampleMatcher
        .matchingAny()
        .withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING);

        Example<Purchase> example = Example.of(purchaseExample, matcher);

        return repository
        .findAll(example, page)
        .stream()
        .map(purchase -> convertPurchaseToOutput(purchase))
        .toList();
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    public Purchase convertInputToPurchase(PurchaseInput input) {

        Long userId = input.getUserId();
        Long carId = input.getCarId();

        Purchase purchase = new Purchase();

        User user = userRepository.findById(userId).get();
        purchase.setUser(user);

        Car car = carRepository.findById(carId).get();
        purchase.setCar(car);

        purchase.setPurchaseDate(LocalDate.now());

        return purchase;
    }

    private PurchaseOutput convertPurchaseToOutput(Purchase purchase) {

        if (purchase == null) {
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        var purchaseMapped = modelMapper.map(purchase, PurchaseOutput.class);
        purchaseMapped.setWarrantyDate(calculateWarrantyDate(purchaseMapped));
        purchaseMapped.setReviewDate(calculateReviewDate(purchaseMapped));
        return purchaseMapped;

    }

    private LocalDate calculateWarrantyDate(PurchaseOutput purchase) {

        if (purchase.getCar().getCategory() == Category.SEMINEW) {
            return purchase.getPurchaseDate().plusMonths(6);
        } else {
            return purchase.getPurchaseDate().plusMonths(12);
        }

    }

    private LocalDate calculateReviewDate(PurchaseOutput purchase) {

        if (purchase.getCar().getCategory() == Category.SEMINEW) {
            return purchase.getPurchaseDate().plusMonths(6);
        } else {
            return purchase.getPurchaseDate().plusMonths(12);
        }

    }
    
}
