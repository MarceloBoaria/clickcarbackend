package com.clickcar.clickcarback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickcar.clickcarback.dtos.purchases.PurchaseInput;
import com.clickcar.clickcarback.dtos.purchases.PurchaseOutput;
import com.clickcar.clickcarback.entities.Purchase;
import com.clickcar.clickcarback.service.PurchaseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/purchases")
@Validated
@CrossOrigin("*")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @GetMapping
    public ResponseEntity<List<PurchaseOutput>> list(Pageable page, Purchase example) {
        List<PurchaseOutput> list = service.list(page, example);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<PurchaseOutput> create(@Valid @RequestBody PurchaseInput purchase) {
        PurchaseOutput output = service.create(purchase);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
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
        http://localhost:8080/purchases/{id}

        @RequestParam GET
        http://localhost:8080/purchases?name=teste  // MÉTODO POR FILTRAGEM!

        @RequestBody POST | PUT  // PERMITE A ENTRADA DOS DADOS COMO JSON!
        JSON
        {
            "name": "Teste",
            ...
        }

    */
    
}
