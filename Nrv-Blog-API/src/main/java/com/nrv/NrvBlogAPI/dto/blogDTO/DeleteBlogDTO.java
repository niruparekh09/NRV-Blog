package com.nrv.NrvBlogAPI.dto.blogDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * DTO class to provide client side a response about deleted blog.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
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
