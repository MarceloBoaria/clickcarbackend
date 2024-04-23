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

import com.clickcar.clickcarback.dtos.users.UserInput;
import com.clickcar.clickcarback.dtos.users.UserOutput;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<User>> list() {
        List<User> list = repository.findAll();
        // return new ResponseEntity<>(list, HttpStatus.OK);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<UserOutput> create(@RequestBody UserInput user) {

        User userToSave = new User();

        userToSave.setName(user.getName());
        userToSave.setEmail(user.getEmail());
        userToSave.setCpf(user.getCpf());
        userToSave.setPhone(user.getPhone());
        userToSave.setPassword(user.getPassword());

        User userSaved = repository.save(userToSave);

        UserOutput output = new UserOutput(

            userSaved.getId(),
            userSaved.getName(),
            userSaved.getEmail(),
            userSaved.getCpf(),
            userSaved.getPhone());

            return new ResponseEntity<>(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public ResponseEntity<User> read(@PathVariable Long id) {
        User user = repository.findById(id).get();
        // return new ResponseEntity<>(user, HttpStatus.OK);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        User userUpdated = repository.save(user);
        // return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        return ResponseEntity.ok(userUpdated);
    }

    @SuppressWarnings("rawtypes") // Para fazer o compilador não emitir warnings.
    @DeleteMapping("/{id}") // Entre chaves para indicar que é um Parâmetro Variável!
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);
        // return new ResponseEntity(HttpStatus.NO_CONTENT);
        return ResponseEntity.noContent().build();
    }

    /*

        http://localhost:8080/swagger-ui.html // DOCUMENTAÇÃO NO SWAGGER!

        @PathVariable GET | PUT | DELETE
        http://localhost:8080/users/{id}

        @RequestParam GET
        http://localhost:8080/users?name=teste  // MÉTODO POR FILTRAGEM!

        @RequestBody POST | PUT  // PERMITE A ENTRADA DOS DADOS COMO JSON!
        JSON
        {
            "name": "Teste",
            ...
        }

    */
    
}
