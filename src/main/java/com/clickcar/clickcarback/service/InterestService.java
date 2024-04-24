package com.clickcar.clickcarback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickcar.clickcarback.entities.Interest;
import com.clickcar.clickcarback.repositories.InterestRepository;

@Service
public class InterestService {

    @Autowired
    private InterestRepository repository;

    @Transactional
    public Interest create(Interest interest) {
        return repository.save(interest);
    }

    public List<Interest> list() {
        return repository.findAll();
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
