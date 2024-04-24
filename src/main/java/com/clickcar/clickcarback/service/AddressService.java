package com.clickcar.clickcarback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickcar.clickcarback.dtos.adresses.AddressInput;
import com.clickcar.clickcarback.dtos.adresses.AddressOutput;
import com.clickcar.clickcarback.entities.Address;
import com.clickcar.clickcarback.repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Transactional
    public AddressOutput create(AddressInput input) {
        Address address = convertInputToAddress(input);
        address = repository.save(address);
        return convertAddressToOutput(address);
    }

    public List<AddressOutput> list() {
        // [address1, address2, address3].map(address -> convertAddressToOutput(address));
        return repository
        .findAll()
        .stream()
        .map(address -> convertAddressToOutput(address))
        .toList();
    }

    public AddressOutput read(Long id) {
        Address address = repository.findById(id).orElse(null);
        return convertAddressToOutput(address);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public AddressOutput update(Long id, AddressInput input) {
        if(repository.existsById(id)) {
            Address address = convertInputToAddress(input);
            address.setId(id);
            address = repository.save(address);
            return convertAddressToOutput(address);
        } else {
            return null;
        }
    }

    private Address convertInputToAddress(AddressInput input) {

        Address address = new Address();

        address.setCep(address.getCep());
        address.setNumber(address.getNumber());
        address.setComplement(address.getComplement());
        return address;

    }

    private AddressOutput convertAddressToOutput(Address address) {

        if (address == null) {
            return null;
        }

        AddressOutput output = new AddressOutput(
            
            address.getId(),
            address.getCep(),
            address.getNumber(),
            address.getComplement());
            return output;

    }
    
}
