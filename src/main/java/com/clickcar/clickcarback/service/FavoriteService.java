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

import com.clickcar.clickcarback.dtos.favorits.FavoriteInput;
import com.clickcar.clickcarback.dtos.favorits.FavoriteOutput;
import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.entities.Favorite;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.repositories.CarRepository;
import com.clickcar.clickcarback.repositories.FavoriteRepository;
import com.clickcar.clickcarback.repositories.UserRepository;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    @Transactional
    public FavoriteOutput create(FavoriteInput input) {

        Long userId = input.getUserId();
        Long carId = input.getCarId();
        
        if (userRepository.existsById(userId) && carRepository.existsById(carId)) {
            Favorite favorite = convertInputToFavorite(input);
            favorite = repository.save(favorite);
            return convertFavoriteToOutput(favorite);

        } else {
            return null;
        }
    }

    public List<FavoriteOutput> list(Pageable page, Favorite favoriteExample) {
        ExampleMatcher matcher = ExampleMatcher
        .matchingAny()
        .withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING);

        Example<Favorite> example = Example.of(favoriteExample, matcher);

        return repository
        .findAll(example, page)
        .stream()
        .map(favorite -> convertFavoriteToOutput(favorite))
        .toList();
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    private Favorite convertInputToFavorite(FavoriteInput input) {

        Long userId = input.getUserId();
        Long carId = input.getCarId();

        Favorite favorite = new Favorite();

        User user = userRepository.findById(userId).get();
        favorite.setUser(user);

        Car car = carRepository.findById(carId).get();
        favorite.setCar(car);

        favorite.setFavoriteDate(LocalDate.now());

        return favorite;
    }

    private FavoriteOutput convertFavoriteToOutput(Favorite favorite) {

        if (favorite == null) {
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(favorite, FavoriteOutput.class);

    }

}
