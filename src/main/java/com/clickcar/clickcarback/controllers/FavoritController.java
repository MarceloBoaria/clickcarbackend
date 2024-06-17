package com.clickcar.clickcarback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickcar.clickcarback.dtos.favorits.FavoritInput;
import com.clickcar.clickcarback.service.FavoritService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/favorits")
@CrossOrigin("*")
public class FavoritController {

    @Autowired
    private FavoritService service;

    @PostMapping
    public ResponseEntity<Boolean> create(@Valid @RequestBody FavoritInput favorit) {
        Boolean output = service.create(favorit);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping
    public ResponseEntity delete(@Valid @RequestBody FavoritInput favorit) {
        service.delete(favorit);
        return ResponseEntity.noContent().build();
    }
    
}
