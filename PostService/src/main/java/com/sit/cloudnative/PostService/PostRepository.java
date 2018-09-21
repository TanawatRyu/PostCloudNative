package com.sit.cloudnative.PostService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{
    // @Query("SELECT p.id FROM Post p WHERE p.user_id = :id ")
    // Long findPostUserId (@Param("id") Long id);
}