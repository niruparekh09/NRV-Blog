package com.nrv.NrvBlogAPI.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddBlogDTO {
    @Column(name = "Blog_Title", nullable = false)
    private String blogTitle;
    @Column(name = "Blog_Content", nullable = false, columnDefinition = "TEXT")
    private String blogContent;
    @Column(name = "Blog_Creation_Time", nullable = false)
    private LocalDate blogCreationDate;

    public AddBlogDTO(String blogTitle, String blogContent) {
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.blogCreationDate = LocalDate.now();
    }
}
