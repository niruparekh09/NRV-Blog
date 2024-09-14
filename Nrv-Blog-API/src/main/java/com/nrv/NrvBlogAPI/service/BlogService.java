package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.dto.APIResponse;
import com.nrv.NrvBlogAPI.dto.blogDTO.AddBlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogCreatedDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.DeleteBlogDTO;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    /*
     * Method To Get A Single Blog
     */
    BlogDTO getBlog(UUID blogId);
    /*
     * Method To Get List Of Blogs
     */
    List<BlogDTO> getAllBlogs();
    /*
     * Method To Add A Blog
     */
    BlogCreatedDTO addBlog(AddBlogDTO blog);
    /*
     * Method To Delete A Blog
     */
    DeleteBlogDTO deleteBlog(UUID blogId);
}
