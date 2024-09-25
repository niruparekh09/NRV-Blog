package com.nrv.NrvBlogAPI.dto.userDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class to get existing userId and updated password of a user from client side.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    @NotNull(message = "UserId Can Not Be Empty")
    private String userId;
    @NotNull(message = "UserPassword can not be empty")
    private String password;
}
