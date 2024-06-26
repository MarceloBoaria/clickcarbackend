package com.clickcar.clickcarback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clickcar.clickcarback.dtos.cars.CarOutput;
import com.clickcar.clickcarback.dtos.users.UserInput;
import com.clickcar.clickcarback.dtos.users.UserOutput;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserOutput>> list(Pageable page, User example) {
        List<UserOutput> list = service.list(page, example);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}/favorits")
    public ResponseEntity<List<CarOutput>> list(@PathVariable Long id) {
        var list = service.listCar(id);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<UserOutput> create(@Valid @RequestBody UserInput user) {
        UserOutput output = service.create(user);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutput> read(@PathVariable Long id) {
        UserOutput user = service.read(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutput> update(@PathVariable Long id, @Valid @RequestBody UserInput input) {
        UserOutput output = service.update(id, input);
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

    @PostMapping("/{id}/photo")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam MultipartFile image) {
      String message = "";
      try {
          service.upload(id, image);
        message = "Uploaded the image successfully: " + image.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(message);
      } catch (Exception e) {
        message =
          "Could not upload the image: " +
          image.getOriginalFilename() +
          ". Error: " +
          e.getMessage();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
      }
    }
  
    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) {
      Resource image = service.getFoto(id);
      
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"").body(image);
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
