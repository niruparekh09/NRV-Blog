package com.nrv.NrvBlogAPI.dto.userDTO;

import com.nrv.NrvBlogAPI.entities.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RegisterUserDTO {
    @NotNull(message = "UserId can not be empty")
    private String userId;
    @NotNull(message = "UserName can not be empty")
    @Size(max = 20)
    private String userName;
    @NotNull(message = "UserPassword Can Not Be Empty")
    private String password;

    public RegisterUserDTO(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}
