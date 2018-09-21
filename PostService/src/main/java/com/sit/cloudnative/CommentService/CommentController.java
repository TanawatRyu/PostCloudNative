package com.sit.cloudnative.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.validation.Valid;

import com.sit.cloudnative.PostService.PostRepository;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;


    @RequestMapping(value="/post/{postId}/comments" , method = RequestMethod.GET)
    public ResponseEntity<Object[]> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId, Pageable pageable) {
        Object [] listPostAllComment_Object = commentService.getAllComments(postId, pageable);
        return new ResponseEntity<Object[]>(listPostAllComment_Object,HttpStatus.OK);
    }
    

    @RequestMapping(value="/post/{postId}/comments/{userId}" , method = RequestMethod.POST)
    public ResponseEntity<Optional<Comment>> createComment(@PathVariable (value = "postId") Long postId, @Valid @RequestBody Comment comment, @PathVariable (value = "userId") Long userId ) {
        Optional<Comment> comment_Object = commentService.create(postId, comment, userId);
        return new ResponseEntity<Optional<Comment>>(comment_Object,HttpStatus.OK);
    }

    @RequestMapping(value="/post/{postId}/comments/{commentId}" , method = RequestMethod.PUT)
    public ResponseEntity<Optional<Object>> updateComment(@PathVariable (value = "postId") Long postId, @PathVariable (value = "commentId") Long commentId, @Valid @RequestBody Comment commentRequest) {
        Optional<Object> updateComment_Object = commentService.update(postId, commentId, commentRequest);
        return new ResponseEntity<Optional<Object>>(updateComment_Object,HttpStatus.OK);
    }

    @RequestMapping(value="/post/{postId}/comments/{commentId}" , method = RequestMethod.DELETE)
    public ResponseEntity<Optional<Object>> deleteComment(@PathVariable (value = "commentId") Long commentId) {
        Optional<Object> deleteComment_object  = commentService.delete(commentId);
        return new ResponseEntity<Optional<Object>>(deleteComment_object,HttpStatus.OK);
    }

}