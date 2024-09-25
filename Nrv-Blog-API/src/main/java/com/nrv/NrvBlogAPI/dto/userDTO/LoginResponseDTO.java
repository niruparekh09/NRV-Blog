package com.nrv.NrvBlogAPI.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class to provide information of logged-in user along with their respective <b><i>Jwt Token</i></b>.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String userId;
    private String jwtToken;
    private String message;
    private String role;
}
