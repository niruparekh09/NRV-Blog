package com.nrv.NrvBlogAPI.log;

public enum UserLogMessages {
    USER_GET("Get User with ID: {}"),
    USER_LOGIN("User Logged In With ID: {}"),
    USER_REGISTER("User Registered With ID: {}"),
    USER_UPDATE("User Updated With ID: {}"),
    USER_GET_LIST("Get All Users"),
    BLOG_DELETED("User Deleted With ID: {}");

    private final String message;

    UserLogMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
