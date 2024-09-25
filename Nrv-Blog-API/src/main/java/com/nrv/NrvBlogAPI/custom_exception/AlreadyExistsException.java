package com.nrv.NrvBlogAPI.custom_exception;

/**
 * Custom exception to throw when client adds something that already exists.
 *
 * @author Nirav Parekh
 * @see java.lang.RuntimeException
 * @since 1.0
 */
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
