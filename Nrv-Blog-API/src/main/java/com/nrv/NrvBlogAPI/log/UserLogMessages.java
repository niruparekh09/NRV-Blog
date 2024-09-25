package com.nrv.NrvBlogAPI.log;

/**
 * Enum class for user log messages.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
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
