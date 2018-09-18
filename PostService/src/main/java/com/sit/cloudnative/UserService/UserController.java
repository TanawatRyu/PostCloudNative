package com.sit.cloudnative.UserService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController{

    @Autowired
    private UserService userService;
    
    //GetList
    @RequestMapping(value="/users" , method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserList(){
        List<User> users = userService.getAllUser();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @RequestMapping(value="/user" , method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User user_object = userService.create(user);
        return new ResponseEntity<User>(user_object,HttpStatus.OK);
    }


    //GetById
    // @RequestMapping(value="/user/{user_id}" , method = RequestMethod.GET)
    // public ResponseEntity<User> getUser(@PathVariable("user_id") int id){
    //     User user = userService.getUserById(id);
    //     return new ResponseEntity<User>(user,HttpStatus.OK);
        
    // } 

}