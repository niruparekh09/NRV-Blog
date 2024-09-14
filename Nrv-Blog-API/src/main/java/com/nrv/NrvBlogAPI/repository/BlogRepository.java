package com.nrv.NrvBlogAPI.repository;

import com.nrv.NrvBlogAPI.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
    // Fetch a blog along with its user (author)
    @Query("SELECT b FROM Blog b JOIN FETCH b.user WHERE b.blogId = :blogId")
    Optional<Blog> findByBlogIdWithUser(@Param("blogId") UUID blogId);
}
