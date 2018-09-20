package com.sit.cloudnative.PostService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{
    // @Query(value = "SELECT p.id,p.title,p.description,p.content,p.user_Id,p.createdAt,p.updatedAt FROM Post p where p.user_Id = :id") 
    // List<Post> findPostById(@Param("id") Long id);

    // List<Post> findAll();

    // Optional<Post> findById(Long postId);

}