package com.nrv.NrvBlogAPI.dto;

import com.nrv.NrvBlogAPI.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTO {
    private UUID blogId;
    private String blogTitle;
    private String blogContent;
    private LocalDate blogCreationDate;
    private User user;
}
