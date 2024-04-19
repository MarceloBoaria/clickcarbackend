package com.clickcar.clickcarback.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Column(unique = true)
    private String email;
    private String cpf;
    private String phone;
    private String password;
    private Boolean isAdmin;

    @OneToOne
    private Address address;
    @OneToOne(optional = true)
    private Photograph photograph;
    @OneToMany
    private List<Car> cars;
    @Embedded
    private Interest interest;
    
}
