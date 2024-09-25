package com.nrv.NrvBlogAPI.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO class to provide Blog information to client side.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBlogDTO {
    private UUID blogId;
    private String blogTitle;
    private String blogContent;
    private LocalDate blogCreationDate;
}
