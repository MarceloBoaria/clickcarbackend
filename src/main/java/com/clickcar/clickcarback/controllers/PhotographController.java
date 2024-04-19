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

import com.clickcar.clickcarback.entities.Photograph;
import com.clickcar.clickcarback.repositories.PhotographRepository;

@RestController
@RequestMapping("/photos")
public class PhotographController {

    @Autowired
    private PhotographRepository repository;

    @GetMapping
    public List<Photograph> list() {
        return repository.findAll();
    }

    @PostMapping
    public Photograph create(@RequestBody Photograph photograph) {
        return repository.save(photograph);
    }

    @GetMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public Photograph read(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PutMapping
    public Photograph update(@RequestBody Photograph photograph) {
        return repository.save(photograph);
    }

    @DeleteMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
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
