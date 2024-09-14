package com.nrv.NrvBlogAPI.service;

import com.nrv.NrvBlogAPI.dto.APIResponse;
import com.nrv.NrvBlogAPI.dto.userDTO.*;

import java.util.List;

public interface UserService {

    /*
     * Method Called When Get A Single User
     */
    UserDTO getUserWithBlogs(String userId);

    /*
     * Method Called When User Logins
     */
    LoginResponseDTO userLogin(LoginUserDTO user);

    /*
     * Method To Register A User
     */
    UserRegisteredDTO userRegister(RegisterUserDTO userRegistration);

    /*
     * Method To Update A Single User
     */
    APIResponse userUpdate(UpdateUserDTO updatedUser);

    /*
     * Method To Get List Of Users
     */
    List<UserDTO> getListOfUsers();

    /*
     * Method To Delete A User
     */
    DeleteUserResponseDto deleteAUser(String userId);
}
