package com.nrv.NrvBlogAPI.custom_exception;

/**
 * Custom exception to throw when client access something unauthorized.
 *
 * @author Nirav Parekh
 * @see java.lang.RuntimeException
 * @since 1.0
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
