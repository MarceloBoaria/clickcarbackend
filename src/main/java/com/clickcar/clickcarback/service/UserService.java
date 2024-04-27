package com.clickcar.clickcarback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickcar.clickcarback.dtos.users.UserInput;
import com.clickcar.clickcarback.dtos.users.UserOutput;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public UserOutput create(UserInput input) {
        User user = convertInputToUser(input);
        user = repository.save(user);
        return convertUserToOutput(user);
    }

    public List<UserOutput> list(Pageable page, User userExample) {
        ExampleMatcher matcher = ExampleMatcher
        .matchingAny()
        .withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING);

        Example<User> example = Example.of(userExample, matcher);

        return repository
        .findAll(example, page)
        .stream()
        .map(user -> convertUserToOutput(user))
        .toList();
    }

    public UserOutput read(Long id) {
        User user = repository.findById(id).orElse(null);
        return convertUserToOutput(user);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public UserOutput update(Long id, UserInput input) {
        if(repository.existsById(id)) {
            User user = convertInputToUser(input);
            user.setId(id);
            user = repository.save(user);
            return convertUserToOutput(user);
        } else {
            return null;
        }
    }

    private User convertInputToUser(UserInput input) {

        User user = new User();

        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setCpf(user.getCpf());
        user.setPhone(user.getPhone());
        user.setPassword(user.getPassword());
        return user;

    }

    private UserOutput convertUserToOutput(User user) {

        if (user == null) {
            return null;
        }

        UserOutput output = new UserOutput(
            
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCpf(),
            user.getPhone());
            return output;

    }
    
}
