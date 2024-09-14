package com.nrv.NrvBlogAPI.repository;

import com.nrv.NrvBlogAPI.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
}
