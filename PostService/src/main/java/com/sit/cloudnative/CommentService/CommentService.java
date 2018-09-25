package com.sit.cloudnative.CommentService;

import java.util.Optional;

import com.sit.cloudnative.PostService.PostRepository;
import com.sit.cloudnative.PostService.PostService;
import com.sit.cloudnative.UserService.UserService;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public Page<Comment> getAllComments(Long postId, Pageable pageable){
        return commentRepository.findByPostId(postId,pageable);  
    }

    public Optional<Comment> create(Long postId,Comment comment, Long user_Id){
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            userService.getUserById(user_Id).map(user -> {
                comment.setUser(user);
                return commentRepository.save(comment);
            });
            return commentRepository.save(comment);
        });
    }

    public Optional<Object> update(Long postId,Long commentId, Comment commentRequest){
        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        });
    }

    public Optional<Object> delete(Long commentId){
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
       });    
    }
}