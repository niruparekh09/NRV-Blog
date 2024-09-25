package com.nrv.NrvBlogAPI.controller;

import com.nrv.NrvBlogAPI.dto.blogDTO.AddBlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogCreatedDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.DeleteBlogDTO;
import com.nrv.NrvBlogAPI.dto.userDTO.UserDTO;
import com.nrv.NrvBlogAPI.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for User API. Base API {@code /api/v1/user}
 *
 * @author Nirav Parekh
 * @see com.nrv.NrvBlogAPI.service.BlogService
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    BlogService service;

    /**
     * Method for {@code GET} request at {@code /api/v1/blog/{id}}. The client will send id of a blog to get
     * the particular blog.
     *
     * @param id Blog id.
     * @return ResponseEntity<BlogDTO> with the operation result.
     * @author Nirav Parekh
     * @see BlogDTO
     * @since 1.0
     */
    @GetMapping("/{id}")
    ResponseEntity<BlogDTO> getBlog(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getBlog(id));
    }

    /**
     * Method for {@code GET} request at {@code /api/v1/user}. The client will send request to get all blogs.
     *
     * @return ResponseEntity<List < UserDTO>> with the operation result.
     * @author Nirav Parekh
     * @see UserDTO
     * @since 1.0
     */
    @GetMapping
    ResponseEntity<List<BlogDTO>> getBlogList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllBlogs());
    }

    /**
     * Method for {@code POST} request at {@code /api/v1/user/{id}}. The client will content of a new blog
     * to add a blog in db.
     *
     * @param newBlog New Blog Content.
     * @return ResponseEntity<BlogCreatedDTO> with the operation result.
     * @author Nirav Parekh
     * @see AddBlogDTO
     * @see BlogCreatedDTO
     * @since 1.0
     */
    @PostMapping("/post")
    ResponseEntity<BlogCreatedDTO> addBlog(@RequestBody @Valid AddBlogDTO newBlog) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addBlog(newBlog));
    }

    /**
     * Method for {@code DELETE} request at {@code /api/v1/blog/{id}}. The client will send id of a blog to delete
     * a particular blog.
     *
     * @param id Blog id.
     * @return ResponseEntity<DeleteBlogDTO> with the operation result.
     * @author Nirav Parekh
     * @see DeleteBlogDTO
     * @since 1.0
     */
    @DeleteMapping("/{id}")
    ResponseEntity<DeleteBlogDTO> deleteBlog(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.deleteBlog(id));
    }
}
