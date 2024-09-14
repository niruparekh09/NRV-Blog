package com.nrv.NrvBlogAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "User_Id")
    private UUID userId;
    @Column(name = "User_Name", nullable = false)
    private String userName;
    @Column(name = "User_Role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "User_Password", nullable = false)
    private String password;
    @Column(name = "User_Creation_Time", nullable = false)
    private LocalDate userCreationDate;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogList = new ArrayList<>();
}
