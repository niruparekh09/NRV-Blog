package com.nrv.NrvBlogAPI.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Template class for any API Response. Can be used to return anywhere we need to provide a formated API Response
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class APIResponse {
    private LocalDateTime timeStamp;
    private String message;

    public APIResponse(String message) {
        super();
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}