package com.nrv.NrvBlogAPI.custom_exception;

/**
 * Custom exception to throw when client access something that is not available with server.
 *
 * @author Nirav Parekh
 * @see java.lang.RuntimeException
 * @since 1.0
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
