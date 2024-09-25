package com.nrv.NrvBlogAPI.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entity class for Blog.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Blog_Id")
    private UUID blogId;

    @Column(name = "Blog_Title", nullable = false)
    private String blogTitle;

    @Column(name = "Blog_Content", nullable = false, columnDefinition = "TEXT")
    private String blogContent;

    @Column(name = "Blog_Creation_Time", nullable = false)
    private LocalDate blogCreationDate;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    @JsonManagedReference
    private User user;

    public Blog(String blogTitle, String blogContent, LocalDate blogCreationDate, User user) {
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.blogCreationDate = blogCreationDate;
        this.user = user;
    }
}
