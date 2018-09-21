package com.sit.cloudnative.PostService;

import java.util.List;
import java.util.Optional;

import com.sit.cloudnative.CommentService.CommentRepository;
import com.sit.cloudnative.UserService.UserRepository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired 
    private UserRepository userRepository; 

    @Autowired
    private CommentRepository commentRepository;

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Object[] getPostById(Long postId , Pageable pageable) {
        Optional<Post> postById = postRepository.findById(postId);
        Page commentPage =  commentRepository.findByPostId(postId, pageable);
        Object [] listPostAllCommentService = new Object[2];
        listPostAllCommentService[0] = postById;
        listPostAllCommentService[1] = commentPage;
        return listPostAllCommentService;         
    }

    public Optional<Post> create(Long userId,Post post){
        return userRepository.findById(userId).map(user -> {
            post.setUser(user);
            return postRepository.save(post);
        });       
    }

    public Optional<Object> update(Long postid,Post postRequest) {
        return postRepository.findById(postid).map(post -> {
            if(postRequest.getTitle() != null){
                post.setTitle(postRequest.getTitle());                
            }else if(postRequest.getDescription() != null){
                post.setDescription(postRequest.getDescription());
            }else if(postRequest.getContent() != null){
                post.setContent(postRequest.getContent());              
            }
            return postRepository.save(post);
       }); 
    }

    public Optional<Object> delete(Long postid) {
        return postRepository.findById(postid).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
       }); 
    }
}