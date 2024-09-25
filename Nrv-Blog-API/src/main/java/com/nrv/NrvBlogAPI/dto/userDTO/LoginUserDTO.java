package com.nrv.NrvBlogAPI.dto.userDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class to get UserId and Password of a user from client side.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDTO {
    @NotNull(message = "UserId can not be null")
    private String userId;
    @NotNull(message = "Password can not be null")
    private String password;
}
