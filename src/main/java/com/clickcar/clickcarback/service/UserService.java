package com.clickcar.clickcarback.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.clickcar.clickcarback.dtos.cars.CarOutput;
import com.clickcar.clickcarback.dtos.users.UserInput;
import com.clickcar.clickcarback.dtos.users.UserOutput;
import com.clickcar.clickcarback.entities.Photograph;
import com.clickcar.clickcarback.entities.User;
import com.clickcar.clickcarback.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private CarService carService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private FilesStorageServiceImp storageService;

    @Transactional
    public UserOutput create(UserInput input) {
        User user = convertInputToUser(input);
        user = repository.save(user);
        return convertUserToOutput(user);
    }

    public List<CarOutput> listCar(Long id) {
        User user = repository.findById(id).orElse(null);
        return user.getFavorits()
                .stream()
                .map(car -> carService.convertCarToOutput(car))
                .toList();
    }

    public List<UserOutput> list(Pageable page, User userExample) {
        ExampleMatcher matcher = ExampleMatcher
                .matchingAny()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

        Example<User> example = Example.of(userExample, matcher);

        return repository
                .findAll(example, page)
                .stream()
                .map(user -> convertUserToOutput(user))
                .toList();
    }

    public UserOutput read(Long id) {
        User user = repository.findById(id).orElse(null);
        return convertUserToOutput(user);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public UserOutput update(Long id, UserInput input) {
        if (repository.existsById(id)) {
            User user = convertInputToUser(input);
            user.setId(id);
            user = repository.save(user);
            return convertUserToOutput(user);
        } else {
            return null;
        }
    }

    public void upload(Long id, MultipartFile image) {
        if (repository.existsById(id)) {
            var filename = storageService.save(image);
            var foto = new Photograph(filename);
            var user = repository.findById(id).get();
            user.setPhotograph(foto);
            repository.save(user);
        }
    }

    public Resource getFoto(Long id) { 
        if(repository.existsById(id)) {
            var user = repository.findById(id).get();
            if (user.getPhotograph() != null) {
                return storageService.load(user.getPhotograph().getImage());
            }
            return null;
        }
        return null;
    }

    private User convertInputToUser(UserInput input) {

        User user = new User();

        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setCpf(input.getCpf());
        user.setPhone(input.getPhone());
        // Criptografar a senha, tanto no update, quanto no create!
        var senhaCriptografada = new BCryptPasswordEncoder().encode(input.getPassword());
        user.setPassword(senhaCriptografada);
        return user;

        // ModelMapper modelMapper = new ModelMapper();
        // return modelMapper.map(input, User.class);

    }

    private UserOutput convertUserToOutput(User user) {

        if (user == null) {
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserOutput.class);

    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        
        User user = repository.findByCpf(cpf);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
            .builder()
            .username(cpf)
            .password(user.getPassword())
            .build();

    }

}
