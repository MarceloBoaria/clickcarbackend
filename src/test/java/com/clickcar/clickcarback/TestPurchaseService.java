package com.clickcar.clickcarback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.clickcar.clickcarback.dtos.purchases.PurchaseInput;
import com.clickcar.clickcarback.dtos.purchases.PurchaseOutput;
import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.entities.Purchase;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.repositories.CarRepository;
import com.clickcar.clickcarback.repositories.PurchaseRepository;
import com.clickcar.clickcarback.repositories.UserRepository;
import com.clickcar.clickcarback.service.PurchaseService;

public class TestPurchaseService {
    
    @Mock
    private PurchaseRepository repository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private PurchaseService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePurchase() {

        var localDateNow = LocalDate.now();

        User user = new User();
        user.setId(1L);

        Car car = new Car();
        car.setId(1L);

        PurchaseInput purchaseInput = new PurchaseInput();
        purchaseInput.setUserId(1L);
        purchaseInput.setCarId(1L);

        Purchase purchase = new Purchase();
        purchase.setId(1L);
        purchase.setUser(user);
        purchase.setCar(car);
        purchase.setPurchaseDate(localDateNow);

        PurchaseOutput expected = new PurchaseOutput();
        expected.setId(1L);
        expected.setReviewDate(localDateNow.plusMonths(6));
        expected.setWarrantyDate(localDateNow.plusMonths(6));
        expected.setPurchaseDate(localDateNow);
        expected.setUser(user);
        expected.setCar(car);

        when(userRepository.existsById(1L)).thenReturn(true);
        when(carRepository.existsById(1L)).thenReturn(true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        when(repository.save(any())).thenReturn(purchase);

        var result = service.create(purchaseInput);
        assertEquals(expected.getCar(), result.getCar());

    }

    @Test
    public void testConvertInputToPurchase() {

        var localDateNow = LocalDate.now();

        User user = new User();
        user.setId(1L);

        Car car = new Car();
        car.setId(1L);

        PurchaseInput input = new PurchaseInput();
        input.setUserId(1L);
        input.setCarId(1L);

        Purchase purchase = new Purchase();
        purchase.setId(1L);
        purchase.setPurchaseDate(localDateNow);
        purchase.setUser(user);
        purchase.setCar(car);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        Purchase expected = service.convertInputToPurchase(input);

        assertNotNull(expected);
        assertEquals(purchase.getUser().getId(), expected.getUser().getId());
        assertEquals(purchase.getCar().getId(), expected.getCar().getId());
        assertEquals(purchase.getPurchaseDate(), expected.getPurchaseDate());

    }
    
}
