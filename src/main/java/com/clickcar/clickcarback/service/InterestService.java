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

import com.clickcar.clickcarback.dtos.interest.InterestInput;
import com.clickcar.clickcarback.dtos.interest.InterestOutput;
import com.clickcar.clickcarback.entities.Car;
import com.clickcar.clickcarback.entities.Interest;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.repositories.CarRepository;
import com.clickcar.clickcarback.repositories.InterestRepository;
import com.clickcar.clickcarback.repositories.UserRepository;

@Service
public class InterestService {

    @Autowired
    private InterestRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    @Transactional
    public InterestOutput create(InterestInput input) {

        Long userId = input.getUserId();
        Long carId = input.getCarId();
        
        if (userRepository.existsById(userId) && carRepository.existsById(carId)) {
            Interest interest = convertInputToInterest(input);
            interest = repository.save(interest);
            return convertInterestToOutput(interest);

        } else {
            return null;
        }
    }

    public List<InterestOutput> list(Pageable page, Interest interestExample) {
        ExampleMatcher matcher = ExampleMatcher
        .matchingAny()
        .withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING);

        Example<Interest> example = Example.of(interestExample, matcher);

        return repository
        .findAll(example, page)
        .stream()
        .map(interest -> convertInterestToOutput(interest))
        .toList();
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    private Interest convertInputToInterest(InterestInput input) {

        Long userId = input.getUserId();
        Long carId = input.getCarId();

        Interest interest = new Interest();

        User user = userRepository.findById(userId).get();
        interest.setUser(user);

        Car car = carRepository.findById(carId).get();
        interest.setCar(car);

        interest.setDateOfInterest(LocalDate.now());

        return interest;
    }

    private InterestOutput convertInterestToOutput(Interest interest) {

        if (interest == null) {
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(interest, InterestOutput.class);

    }

}
