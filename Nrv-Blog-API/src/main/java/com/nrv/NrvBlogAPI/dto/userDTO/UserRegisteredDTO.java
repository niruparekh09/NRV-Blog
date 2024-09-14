package com.nrv.NrvBlogAPI.dto.userDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nrv.NrvBlogAPI.entities.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredDTO {

    private String userId;

    private String userName;

    private Role role;

    private LocalDate userCreationDate;
}
