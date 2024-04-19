package com.clickcar.clickcarback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickcar.clickcarback.entities.Address;
import com.clickcar.clickcarback.repositories.AddressRepository;

@RestController
@RequestMapping("/adresses")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @GetMapping
    public List<Address> list() {
        return repository.findAll();
    }

    @PostMapping
    public Address create(@RequestBody Address address) {
        return repository.save(address);
    }

    @GetMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public Address read(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PutMapping
    public Address update(@RequestBody Address address) {
        return repository.save(address);
    }

    @DeleteMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    /*

        http://localhost:8080/swagger-ui.html // DOCUMENTAÇÃO NO SWAGGER!

        @PathVariable GET | PUT | DELETE
        http://localhost:8080/adresses/{id}

        @RequestParam GET
        http://localhost:8080/adresses?name=teste  // MÉTODO POR FILTRAGEM!

        @RequestBody POST | PUT  // PERMITE A ENTRADA DOS DADOS COMO JSON!
        JSON
        {
            "name": "Teste",
            ...
        }

    */
    
}
