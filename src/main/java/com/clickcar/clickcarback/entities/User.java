package com.clickcar.clickcarback.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(unique = true)
    private String cpf;
    private String phone;
    private String password;
    private Boolean isAdmin;

    @OneToOne(optional = true)
    private Address address;
    @OneToOne(optional = true)
    private Photograph photograph;
    @ManyToMany
    private List<Car> favorits;
    
}
