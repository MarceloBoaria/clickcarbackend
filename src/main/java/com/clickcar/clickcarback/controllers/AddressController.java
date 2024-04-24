package com.clickcar.clickcarback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickcar.clickcarback.dtos.adresses.AddressInput;
import com.clickcar.clickcarback.dtos.adresses.AddressOutput;
import com.clickcar.clickcarback.service.AddressService;

@RestController
@RequestMapping("/adresses")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<AddressOutput>> list() {
        List<AddressOutput> list = service.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<AddressOutput> create(@RequestBody AddressInput address) {
        AddressOutput output = service.create(address);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public ResponseEntity<AddressOutput> read(@PathVariable Long id) {
        AddressOutput address = service.read(id);
        if(address == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressOutput> update(@PathVariable Long id, @RequestBody AddressInput input) {
        AddressOutput output = service.update(id, input);
        if(output == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(output);
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
