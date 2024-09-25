package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.dto.APIResponse;
import com.nrv.NrvBlogAPI.dto.userDTO.*;

import java.util.List;

/**
 * Interface for user service
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public interface UserService {

    /**
     * Method to get a single user
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    UserDTO getUserWithBlogs(String userId);

    /**
     * Method to log in a user.
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    LoginResponseDTO userLogin(LoginUserDTO user);

    /**
     * Method to register a user
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    UserRegisteredDTO userRegister(RegisterUserDTO userRegistration);

    /**
     * Method to update a user
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    APIResponse userUpdate(UpdateUserDTO updatedUser);

    /**
     * Method to get list of users
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    List<UserDTO> getListOfUsers();

    /**
     * Method to delete a user
     *
     * @author Nirav Parekh
     * @since 1.0
     */
    DeleteUserResponseDto deleteAUser(String userId);
}
