package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.custom_exception.AlreadyExistsException;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service class for Blog. Here are all the Business logic is present for various blog function.
 *
 * @author Nirav Parekh
 * @see BlogService
 * @since 1.0
 */
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
        logger.info(BlogLogMessages.BLOG_GET.getMessage(), blogId);
        return mapper.map(blog, BlogDTO.class);
    }

    @Override
    public List<BlogDTO> getAllBlogs() {
        List<Blog> blogList = blogRepository.findAll();
        logger.info(BlogLogMessages.BLOG_GET_LIST.getMessage());
        return blogList
                .stream()
                .map(blog -> mapper.map(blog, BlogDTO.class))
                .toList();
    }

    @Override
    public BlogCreatedDTO addBlog(AddBlogDTO blog) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!blog.getUserId().equals(currentUsername)) {
            throw new RuntimeException("You are not authorized to Add a blog in other's ID");
        }
        if (checkBlogTitle(blog.getBlogTitle())) {
            throw new AlreadyExistsException("Blog with this title Already Exists");
        }
        if(blog.getBlogTitle().isEmpty() || blog.getBlogContent().isEmpty()){
            throw new RuntimeException("Title or Content Can Not Be Empty");
        }
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
        logger.info(BlogLogMessages.BLOG_CREATED.getMessage(), savedBlog.getBlogId());
        return new BlogCreatedDTO("Blog created Successfully", savedBlogDTO);
    }

    //Admin Only
    @Override
    public DeleteBlogDTO deleteBlog(UUID blogId) {
        // Get the currently authenticated user
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Blog deleteBlog = blogRepository.findByBlogIdWithUser(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog with this ID Not present"));
        if (!deleteBlog.getUser().getUserId().equals(currentUsername)) {
            if (!isAdmin()) {
                throw new RuntimeException("You are not authorized to delete this blog");
            }
        }
        blogRepository.delete(deleteBlog);
        logger.info(BlogLogMessages.BLOG_DELETED.getMessage(), blogId);
        return new DeleteBlogDTO(blogId + " Deleted Successfully", LocalDateTime.now());
    }

    private boolean checkBlogTitle(String newTitle) {
        return blogRepository
                .findBlogTitleList()
                .stream()
                .anyMatch(title -> title.equals(newTitle));
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("In isAdmin. Role: {}", auth);
        return auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

}
