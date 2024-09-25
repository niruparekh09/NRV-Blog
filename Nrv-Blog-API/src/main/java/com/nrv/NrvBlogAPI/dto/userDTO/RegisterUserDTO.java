package com.nrv.NrvBlogAPI.dto.userDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class to get information of a new user from client side.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
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
