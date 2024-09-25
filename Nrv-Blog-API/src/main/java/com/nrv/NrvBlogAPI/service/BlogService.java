package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.dto.blogDTO.AddBlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogCreatedDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.DeleteBlogDTO;

import java.util.List;
import java.util.UUID;

/**
 * Interface for blog service
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public interface BlogService {
    /**
     * Method to get a single blog.
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    BlogDTO getBlog(UUID blogId);

    /**
     * Method to get list of blogs.
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    List<BlogDTO> getAllBlogs();

    /**
     * Method to add a blog.
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    BlogCreatedDTO addBlog(AddBlogDTO blog);

    /**
     * Method to delete a blog.
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    DeleteBlogDTO deleteBlog(UUID blogId);
}
