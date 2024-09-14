package com.nrv.NrvBlogAPI.dto.userDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nrv.NrvBlogAPI.entities.Blog;
import com.nrv.NrvBlogAPI.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String userName;
    private Role role;
    private LocalDate userCreationDate;
    private List<UserBlogDTO> blogList = new ArrayList<>();
}
