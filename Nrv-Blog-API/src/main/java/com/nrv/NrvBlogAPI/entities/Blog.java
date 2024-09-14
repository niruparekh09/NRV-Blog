package com.nrv.NrvBlogAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

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
    private User user;
}
