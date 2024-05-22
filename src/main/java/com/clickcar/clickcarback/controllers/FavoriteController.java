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

import com.clickcar.clickcarback.dtos.favorits.FavoriteInput;
import com.clickcar.clickcarback.dtos.favorits.FavoriteOutput;
import com.clickcar.clickcarback.entities.Favorite;
import com.clickcar.clickcarback.service.FavoriteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/favorits")
@Validated
@CrossOrigin("*")
public class FavoriteController {

    @Autowired
    private FavoriteService service;

    @GetMapping
    public ResponseEntity<List<FavoriteOutput>> list(Pageable page, Favorite example) {
        List<FavoriteOutput> list = service.list(page, example);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<FavoriteOutput> create(@Valid @RequestBody FavoriteInput favorite) {
        FavoriteOutput output = service.create(favorite);
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
        http://localhost:8080/favorits/{id}

        @RequestParam GET
        http://localhost:8080/favorits?name=teste  // MÉTODO POR FILTRAGEM!

        @RequestBody POST | PUT  // PERMITE A ENTRADA DOS DADOS COMO JSON!
        JSON
        {
            "name": "Teste",
            ...
        }

    */
    
}
