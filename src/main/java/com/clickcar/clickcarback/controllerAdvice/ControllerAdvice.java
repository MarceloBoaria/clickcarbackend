package com.clickcar.clickcarback.controllerAdvice;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerAdvice {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler({NoSuchElementException.class, EntityNotFoundException.class})
    public ResponseEntity treatNotFound() {
        return ResponseEntity.notFound().build();
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity treatBadRequest(ConstraintViolationException exception) {
        // var messages = exception.getConstraintViolations()
        //                         .stream()
        //                         .map(violation -> violation.getMessage())
        //                         .toList();
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    
}
