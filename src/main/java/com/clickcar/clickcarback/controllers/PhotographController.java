package com.clickcar.clickcarback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.clickcar.clickcarback.entities.Photograph;
import com.clickcar.clickcarback.service.PhotographService;

@RestController
@RequestMapping("/photos")
public class PhotographController {

    @Autowired
    private PhotographService service;

    @GetMapping
    public ResponseEntity<List<Photograph>> list(Pageable page, Photograph example) {
        List<Photograph> list = service.list(page, example);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Photograph> create(@RequestBody Photograph photograph) {
        Photograph newPhotograph = service.create(photograph);
        return new ResponseEntity<>(newPhotograph, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public ResponseEntity<Photograph> read(@PathVariable Long id) {
        Photograph photograph = service.read(id);
        if(photograph == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(photograph);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photograph> update(@PathVariable Long id, @RequestBody Photograph photograph) {
        Photograph editPhotograph = service.update(id, photograph);
        if(editPhotograph == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editPhotograph);
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
        http://localhost:8080/photos/{id}

        @RequestParam GET
        http://localhost:8080/photos?name=teste  // MÉTODO POR FILTRAGEM!

        @RequestBody POST | PUT  // PERMITE A ENTRADA DOS DADOS COMO JSON!
        JSON
        {
            "name": "Teste",
            ...
        }

    */
    
}
