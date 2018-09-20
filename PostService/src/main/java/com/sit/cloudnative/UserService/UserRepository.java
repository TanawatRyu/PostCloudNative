package com.sit.cloudnative.UserService;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    // @Query("SELECT u.id,u.firstname,u.lastname FROM User u where u.id = :id") 
    // User findPostById(@Param("id") Long id);
}