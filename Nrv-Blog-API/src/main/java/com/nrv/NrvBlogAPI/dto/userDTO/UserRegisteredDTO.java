package com.nrv.NrvBlogAPI.dto.userDTO;

import com.nrv.NrvBlogAPI.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO class to provide information of the newly registered user to client side.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredDTO {

    private String userId;

    private String userName;

    private Role role;

    private LocalDate userCreationDate;
}
