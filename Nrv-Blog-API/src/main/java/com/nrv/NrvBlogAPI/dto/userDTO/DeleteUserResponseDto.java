package com.nrv.NrvBlogAPI.dto.userDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeleteUserResponseDto {
    private String responseMessage;
    private LocalDateTime deletionDate;

    public DeleteUserResponseDto(String userId) {
        this.responseMessage = "User " + userId + " Deleted";
        this.deletionDate = LocalDateTime.now();
    }
}
