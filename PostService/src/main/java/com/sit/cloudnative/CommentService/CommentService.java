package com.sit.cloudnative.CommentService;

import java.util.Optional;

import com.sit.cloudnative.PostService.Post;
import com.sit.cloudnative.PostService.PostRepository;
import com.sit.cloudnative.PostService.PostService;
import com.sit.cloudnative.UserService.User;
import com.sit.cloudnative.UserService.UserAdapter;

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
    private PostService postService;

    @Autowired
    private UserAdapter userAdapter;

    public Page<Comment> getAllComments(Long postId, Pageable pageable){
        return commentRepository.findByPostId(postId,pageable);  
    }

    public Optional<Comment> create(Long postId,Comment comment, Long user_Id){
        return postRepository.findById(postId).map(post -> {
            User user = userAdapter.getUserDetail(user_Id);
            comment.setPost(post);
            comment.setUser(user);
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