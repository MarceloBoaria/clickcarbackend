package com.clickcar.clickcarback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickcar.clickcarback.entities.Address;
import com.clickcar.clickcarback.service.AddressService;

@RestController
@RequestMapping("/adresses")
@CrossOrigin("*")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<Address>> list(Pageable page, Address example) {
        List<Address> list = service.list(page, example);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address) {
        Address newAddress = service.create(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public ResponseEntity<Address> read(@PathVariable Long id) {
        Address address = service.read(id);
        if(address == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address) {
        Address editAddress = service.update(id, address);
        if(editAddress == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editAddress);
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        // return new ResponseEntity(HttpStatus.NO_CONTENT);
        return ResponseEntity.noContent().build();
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
