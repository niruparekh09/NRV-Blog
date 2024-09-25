package com.nrv.NrvBlogAPI.repository;

import com.nrv.NrvBlogAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Method to get a user along with the blogs they created.
     * It uses Join Fetch to avoid using Eager fetch type.
     *
     * @param userId User Id
     * @return Optional<User>
     * @author Nirav Parekh
     * @since 1.0
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.blogList WHERE u.userId = :userId")
    Optional<User> findByUserIdWithBlogs(@Param("userId") String userId);

    /**
     * Method to get all users along with the blogs that they created.
     * It uses Join Fetch to avoid using Eager fetch type.
     *
     * @return Optional<Blog>
     * @author Nirav Parekh
     * @since 1.0
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.blogList")
    List<User> findAllWithBlogs();

    /**
     * Method to get all userIds.
     *
     * @return List<String>
     * @author Nirav Parekh
     * @since 1.0
     */
    @Query("SELECT u.userId FROM User u")
    List<String> findUserIdList();
}
