package com.nrv.NrvBlogAPI.log;

public enum BlogLogMessages {
    BLOG_CREATED("Blog Created with ID: {}"),
    BLOG_GET("Get Blog with ID: {}"),
    BLOG_GET_LIST("Get All Blogs"),
    BLOG_DELETED("Blog Deleted With ID: {}");

    private final String message;

    BlogLogMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
