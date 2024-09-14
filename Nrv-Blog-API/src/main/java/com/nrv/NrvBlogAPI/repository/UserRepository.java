package com.nrv.NrvBlogAPI.repository;

import com.nrv.NrvBlogAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // Fetch a user along with their blogs
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.blogList WHERE u.userId = :userId")
    Optional<User> findByUserIdWithBlogs(@Param("userId") String userId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.blogList")
    List<User> findAllWithBlogs();
}
