package com.nrv.NrvBlogAPI.dto.blogDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogCreatedDTO {
    String message;
    BlogDTO blog;
}
