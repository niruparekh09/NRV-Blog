package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.dto.AddBlogDTO;
import com.nrv.NrvBlogAPI.dto.BlogDTO;
import com.nrv.NrvBlogAPI.dto.DeleteBlogDTO;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    BlogDTO getSingleBlog(UUID blogId);
    List<BlogDTO> getAllBlogs();
    List<AddBlogDTO> addBlog();
    DeleteBlogDTO deleteBlog(UUID blogId);
}
