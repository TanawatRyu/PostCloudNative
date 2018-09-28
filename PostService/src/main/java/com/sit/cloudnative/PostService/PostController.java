package com.sit.cloudnative.PostService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PostController{

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
    
    //GetList
    @RequestMapping(value="/posts" , method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPostList(){
        List<Post> posts = postService.getAllPost();
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @RequestMapping(value="/post/{post_id}" , method = RequestMethod.GET)
    public ResponseEntity<Optional<Post>> getPostByPostId(@PathVariable("post_id") Long id) {
        Optional<Post> listPostWithComments = postService.getPostById(id);
        return new ResponseEntity<Optional<Post>>(listPostWithComments,HttpStatus.OK);

    }

    @RequestMapping(value="/post/{userId}" , method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@PathVariable (value = "userId") Long userId, @Valid @RequestBody Post post){
        Post post_Object = postService.create(userId,post);
        return new ResponseEntity<Post>(post_Object,HttpStatus.OK);
    }

    @RequestMapping(value="/post/{post_id}" , method = RequestMethod.PUT)
    public ResponseEntity<Optional<Object>> updateByPostId(@PathVariable("post_id") Long id ,@Valid @RequestBody Post postRequest){
        Optional<Object> updatePost_Object = postService.update(id,postRequest);
        return new ResponseEntity<Optional<Object>>(updatePost_Object,HttpStatus.OK);
    }

    @RequestMapping(value="/post/{post_id}" , method = RequestMethod.DELETE)
    public ResponseEntity<Optional<Object>> deleteByPostId(@PathVariable("post_id") Long id){
        Optional<Object> deletePost_Object = postService.delete(id);
        return new ResponseEntity<Optional<Object>>(deletePost_Object,HttpStatus.OK);
    }

}