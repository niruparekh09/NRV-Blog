package com.nrv.NrvBlogAPI.dto.blogDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class to provide client side a response when a new blog is created.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogCreatedDTO {
    String message;
    BlogDTO blog;
}
