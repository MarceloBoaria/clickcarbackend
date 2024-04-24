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

import com.clickcar.clickcarback.dtos.cars.CarInput;
import com.clickcar.clickcarback.dtos.cars.CarOutput;
import com.clickcar.clickcarback.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping
    public ResponseEntity<List<CarOutput>> list() {
        List<CarOutput> list = service.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CarOutput> create(@RequestBody CarInput car) {
        CarOutput output = service.create(car);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public ResponseEntity<CarOutput> read(@PathVariable Long id) {
        CarOutput car = service.read(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarOutput> update(@PathVariable Long id, @RequestBody CarInput input) {
        CarOutput output = service.update(id, input);
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
        http://localhost:8080/cars/{id}

        @RequestParam GET
        http://localhost:8080/cars?name=teste  // MÉTODO POR FILTRAGEM!

        @RequestBody POST | PUT  // PERMITE A ENTRADA DOS DADOS COMO JSON!
        JSON
        {
            "name": "Teste",
            ...
        }

    */
    
}
