package com.nrv.NrvBlogAPI.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeleteBlogDTO {
    private String response;
    private LocalDateTime deletionTime;

    public DeleteBlogDTO(String title, LocalDateTime deletionTime) {
        this.response = "Blog " + title + " deleted";
        this.deletionTime = LocalDateTime.now();
    }
}
