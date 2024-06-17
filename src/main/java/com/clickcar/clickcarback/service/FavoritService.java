package com.clickcar.clickcarback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickcar.clickcarback.dtos.favorits.FavoritInput;
import com.clickcar.clickcarback.repositories.CarRepository;
import com.clickcar.clickcarback.repositories.UserRepository;

@Service
public class FavoritService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public Boolean create(FavoritInput favoritInput) {
        var user = userRepository.findById(favoritInput.getUserId()).get();
        var car = carRepository.findById(favoritInput.getCarId()).get();
        user.getFavorits().add(car);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public void delete(FavoritInput favoritInput) {
        var user = userRepository.findById(favoritInput.getUserId()).get();
        var car = carRepository.findById(favoritInput.getCarId()).get();
        user.getFavorits().remove(car);
        userRepository.save(user);
    }
    
}
