package com.clickcar.clickcarback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Address> list() {
        return repository.findAll();
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
