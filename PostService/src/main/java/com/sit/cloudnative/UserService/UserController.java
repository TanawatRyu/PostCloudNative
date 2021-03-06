package com.sit.cloudnative.UserService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController{


    @Autowired
    private UserAdapter userAdapter;
    
    //GetList
    @RequestMapping(value="/users" , method = RequestMethod.GET)
    public ResponseEntity<User[]> getUserList(){
        User[] users = userAdapter.getAllUserDetail();
        return new ResponseEntity<User[]>(users,HttpStatus.OK);
    }

    @RequestMapping(value="/user/{user_id}" , method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUserId(@PathVariable("user_id") Long id) {
        User user = userAdapter.getUserDetail(id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
        
    } 

    @RequestMapping(value="/user" , method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User user_Object = userAdapter.create(user);
        return new ResponseEntity<User>(user_Object,HttpStatus.OK);
    }

    // @RequestMapping(value="/user/{userId}" , method = RequestMethod.PUT)
    // public ResponseEntity<Optional<User>> updateUser(@PathVariable (value = "userId") Long userId, @Valid @RequestBody User userRequest){
    //     Optional<User> updateUser_Object = userService.update(userId,userRequest);
    //     return new ResponseEntity<Optional<User>>(updateUser_Object,HttpStatus.OK);
    // }

    // @RequestMapping(value="/user/{userId}" , method = RequestMethod.DELETE)
    // public ResponseEntity<Optional<Object>> deleteUser(@PathVariable (value = "userId") Long userId){
    //     Optional<Object> deleteUser_Object = userService.delete(userId);
    //     return new ResponseEntity<Optional<Object>>(deleteUser_Object,HttpStatus.OK);
    // }

}