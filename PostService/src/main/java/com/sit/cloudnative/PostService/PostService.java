package com.sit.cloudnative.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }
    // public Optional<User> getUserById(Long id){
    //     return userRepository.findById(id);
    // }

    public Post create(Post Post){
        return postRepository.save(Post);
    }
}