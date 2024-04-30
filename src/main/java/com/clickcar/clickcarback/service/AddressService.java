package com.clickcar.clickcarback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickcar.clickcarback.entities.Address;
import com.clickcar.clickcarback.repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Transactional
    public Address create(Address address) {
        return repository.save(address);
    }

    public List<Address> list(Pageable page, Address addressExample) {
        ExampleMatcher matcher = ExampleMatcher
        .matchingAny()
        .withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING);

        Example<Address> example = Example.of(addressExample, matcher);

        return repository
        .findAll(example, page).toList();
    }

    public Address read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Address update(Long id, Address address) {
        if(repository.existsById(id)) {
            address.setId(id);
            return repository.save(address);
        } else {
            return null;
        }
    }
    
}
