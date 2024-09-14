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

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService service;

    // POST: "/api/v1/user/login"
    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginUserDTO loginUserDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.userLogin(loginUserDTO));
    }

    // GET: "/api/v1/user/{id}"
    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getUserWithBlogs(id));
    }

    // POST: "/api/v1/user/register"
    @PostMapping("/register")
    ResponseEntity<UserRegisteredDTO> register(@RequestBody @Valid RegisterUserDTO newUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.userRegister(newUser));
    }

    // PUT: "/api/v1/user/{id}"
    @PutMapping("/{id}")
    ResponseEntity<APIResponse> updateUser(@RequestBody @Valid UpdateUserDTO updatedUser) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.userUpdate(updatedUser));
    }

    // GET: "/api/v1/user"
    @GetMapping
    ResponseEntity<List<UserDTO>> getUserList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getListOfUsers());
    }

    // DELETE: "/api/v1/user/{id}"
    @DeleteMapping("/{id}")
    ResponseEntity<DeleteUserResponseDto> deleteUser(@PathVariable @Valid String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.deleteAUser(id));
    }
}
