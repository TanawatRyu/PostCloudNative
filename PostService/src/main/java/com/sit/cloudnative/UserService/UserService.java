package com.sit.cloudnative.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public Optional<User> update(Long userId, User userRequest){
        return userRepository.findById(userId).map(user -> {
            user.setFirstname(userRequest.getFirstname());
            user.setLastname(userRequest.getLastname());
            return userRepository.save(user);
        });
    }

    public Optional<Object> delete(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
       }); 
    }
}