package com.clickcar.clickcarback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickcar.clickcarback.entities.Photograph;
import com.clickcar.clickcarback.repositories.PhotographRepository;

import jakarta.transaction.Transactional;

@Service
public class PhotographService {

    @Autowired
    private PhotographRepository repository;

    @Transactional
    public Photograph create(Photograph photograph) {
        return repository.save(photograph);
    }

    public List<Photograph> list() {
        return repository
        .findAll(); // DÚVIDA PARA SABER SE VAMOS BUSCAR TODAS AS FOTOGRAFIAS SALVAS, OU SE ISSO NÃO É NECESSÁRIO <---|
    }

    public Photograph read(Long id) {
        Photograph photograph = repository.findById(id).orElse(null);
        return photograph;
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Photograph update(Long id, Photograph photograph) {
        if(repository.existsById(id)) {
            photograph.setId(id);
            photograph = repository.save(photograph);
            return photograph;
        } else {
            return null;
        }
    }
    
}
