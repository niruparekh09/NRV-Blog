package com.nrv.NrvBlogAPI.dto.blogDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO class to get information of new blog from client side.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBlogDTO {
    @NotNull(message = "UserId Can Not Be Empty")
    private String userId;
    @NotNull(message = "BlogTitle Can Not Be Empty")
    private String blogTitle;
    @NotNull(message = "BlogContent Can Not Be Empty")
    private String blogContent;
}
