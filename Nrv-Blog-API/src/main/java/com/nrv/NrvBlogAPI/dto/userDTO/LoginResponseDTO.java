package com.nrv.NrvBlogAPI.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String userId;
    private String jwtToken;
    private String message;
    private String role;
}
