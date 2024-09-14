package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.custom_exception.ResourceNotFoundException;
import com.nrv.NrvBlogAPI.dto.blogDTO.AddBlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogCreatedDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.BlogDTO;
import com.nrv.NrvBlogAPI.dto.blogDTO.DeleteBlogDTO;
import com.nrv.NrvBlogAPI.entities.Blog;
import com.nrv.NrvBlogAPI.entities.User;
import com.nrv.NrvBlogAPI.log.BlogLogMessages;
import com.nrv.NrvBlogAPI.repository.BlogRepository;
import com.nrv.NrvBlogAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public BlogDTO getBlog(UUID blogId) {
        Blog blog = blogRepository.findByBlogIdWithUser(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog with That Id Not Found"));
        logger.trace(BlogLogMessages.BLOG_GET.getMessage(), blogId);
        return mapper.map(blog, BlogDTO.class);
    }

    @Override
    public List<BlogDTO> getAllBlogs() {
        List<Blog> blogList = blogRepository.findAll();
        logger.trace(BlogLogMessages.BLOG_GET_LIST.getMessage());
        return blogList
                .stream()
                .map(blog -> mapper.map(blog, BlogDTO.class))
                .toList();
    }

    @Override
    public BlogCreatedDTO addBlog(AddBlogDTO blog) {
        User user = userRepository
                .findById(blog.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with That Id Not Found"));
        Blog newBlog = new Blog(
                blog.getBlogTitle(),
                blog.getBlogContent(),
                LocalDate.now(),
                user
        );
        Blog savedBlog = blogRepository.save(newBlog);
        BlogDTO savedBlogDTO = mapper.map(savedBlog, BlogDTO.class);
        logger.trace(BlogLogMessages.BLOG_CREATED.getMessage(), savedBlog.getBlogId());
        return new BlogCreatedDTO("Blog created Successfully", savedBlogDTO);
    }

    //Admin Only
    @Override
    public DeleteBlogDTO deleteBlog(UUID blogId) {
        blogRepository.deleteById(blogId);
        logger.trace(BlogLogMessages.BLOG_DELETED.getMessage(), blogId);
        return new DeleteBlogDTO("Blog Deleted Successfully", LocalDateTime.now());
    }
}
