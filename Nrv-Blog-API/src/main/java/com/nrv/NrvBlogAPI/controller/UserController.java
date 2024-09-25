package com.nrv.NrvBlogAPI.controller;

import com.nrv.NrvBlogAPI.dto.APIResponse;
import com.nrv.NrvBlogAPI.dto.userDTO.*;
import com.nrv.NrvBlogAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for User API. Base API {@code /api/v1/user}
 *
 * @author Nirav Parekh
 * @see com.nrv.NrvBlogAPI.service.UserService
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService service;

    /**
     * Method for {@code POST} request at {@code /api/v1/user/login}. The client will send their userId and
     * password and get LoginResponseDTO in return which has JWT Token and other information
     *
     * @param loginUserDTO User Information.
     * @return ResponseEntity<LoginResponseDTO> with the operation result.
     * @author Nirav Parekh
     * @see LoginUserDTO
     * @see LoginResponseDTO
     * @since 1.0
     */
    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginUserDTO loginUserDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.userLogin(loginUserDTO));
    }

    /**
     * Method for {@code POST} request at {@code /api/v1/user/register}. The client will send their userId, username and
     * password. This will register new user and add information in backend
     *
     * @param newUser New User Information.
     * @return ResponseEntity<UserRegisteredDTO> with the operation result.
     * @author Nirav Parekh
     * @see RegisterUserDTO
     * @see UserRegisteredDTO
     * @since 1.0
     */
    @PostMapping("/register")
    ResponseEntity<UserRegisteredDTO> register(@RequestBody @Valid RegisterUserDTO newUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.userRegister(newUser));
    }

    /**
     * Method for {@code GET} request at {@code /api/v1/user/{id}}. The client will send id of a user to get
     * information of a particular user.
     *
     * @param id User id.
     * @return ResponseEntity<UserDTO> with the operation result.
     * @author Nirav Parekh
     * @see UserDTO
     * @since 1.0
     */
    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getUserWithBlogs(id));
    }

    /**
     * Method for {@code PUT} request at {@code /api/v1/user/{id}}. The client will send the updated information of
     * Client.
     *
     * @param updatedUser Updated information of user
     * @return ResponseEntity<APIResponse> with the operation result.
     * @author Nirav Parekh
     * @see APIResponse
     * @see UpdateUserDTO
     * @since 1.0
     */
    @PutMapping("/{id}")
    ResponseEntity<APIResponse> updateUser(@RequestBody @Valid UpdateUserDTO updatedUser) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.userUpdate(updatedUser));
    }

    /**
     * Method for {@code GET} request {@code /api/v1/user}. The client will send the request here to get information of
     * all the employees.
     *
     * @return ResponseEntity<List < UserDTO>> with the operation result
     * @author Nirav Parekh
     * @see UserDTO
     * @since 1.0
     */
    @GetMapping
    ResponseEntity<List<UserDTO>> getUserList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getListOfUsers());
    }

    /**
     * Method for {@code DELETE} request at {@code /api/v1/user/{id}}. The client will send id of a user to delete
     * a particular user.
     *
     * @param id User id.
     * @return ResponseEntity<UserDTO> with the operation result.
     * @author Nirav Parekh
     * @see DeleteUserResponseDto
     * @since 1.0
     */
    @DeleteMapping("/{id}")
    ResponseEntity<DeleteUserResponseDto> deleteUser(@PathVariable @Valid String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.deleteAUser(id));
    }
}
