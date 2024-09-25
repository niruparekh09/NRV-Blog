package com.nrv.NrvBlogAPI.repository;

import com.nrv.NrvBlogAPI.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Blog.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public interface BlogRepository extends JpaRepository<Blog, UUID> {

    /**
     * Method to get a blog along with the user that created it.
     * It uses Join Fetch to avoid using Eager fetch type.
     *
     * @param blogId Blog Id
     * @return Optional<Blog>
     * @author Nirav Parekh
     * @since 1.0
     */
    @Query("SELECT b FROM Blog b JOIN FETCH b.user WHERE b.blogId = :blogId")
    Optional<Blog> findByBlogIdWithUser(@Param("blogId") UUID blogId);

    /**
     * Method to get all blog titles.
     *
     * @return List<String>
     * @author Nirav Parekh
     * @since 1.0
     */
    @Query("SELECT b.blogTitle from Blog b")
    List<String> findBlogTitleList();
}
