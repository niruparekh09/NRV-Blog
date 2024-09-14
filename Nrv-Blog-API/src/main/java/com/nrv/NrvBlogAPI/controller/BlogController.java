package com.nrv.NrvBlogAPI.controller;

import com.nrv.NrvBlogAPI.dto.blogDTO.AddBlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogCreatedDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.DeleteBlogDTO;
import com.nrv.NrvBlogAPI.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    BlogService service;

    // GET: /api/v1/blog/{id}
    @GetMapping("/{id}")
    ResponseEntity<BlogDTO> getBlog(@PathVariable @Valid UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getBlog(id));
    }

    // GET: /api/v1/blog
    @GetMapping
    ResponseEntity<List<BlogDTO>> getBlogList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllBlogs());
    }

    // POST: /api/vi/blog/post
    @PostMapping("/post")
    ResponseEntity<BlogCreatedDTO> addBlog(@RequestBody @Valid AddBlogDTO newBlog) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addBlog(newBlog));
    }

    // DELETE: /api/vi/blog/{id}
    @DeleteMapping("/{id}")
    ResponseEntity<DeleteBlogDTO> deleteBlog(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.deleteBlog(id));
    }
}
