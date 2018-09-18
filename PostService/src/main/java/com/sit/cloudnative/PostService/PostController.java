package com.sit.cloudnative.PostService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PostController{

    @Autowired
    private PostService postService;
    
    //GetList
    @RequestMapping(value="/posts" , method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPostList(){
        List<Post> posts = postService.getAllPost();
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @RequestMapping(value="/post" , method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        Post post_object = postService.create(post);
        return new ResponseEntity<Post>(post_object,HttpStatus.OK);
    }


    //GetById
    // @RequestMapping(value="/user/{user_id}" , method = RequestMethod.GET)
    // public ResponseEntity<User> getUser(@PathVariable("user_id") int id){
    //     User user = userService.getUserById(id);
    //     return new ResponseEntity<User>(user,HttpStatus.OK);
        
    // } 

}