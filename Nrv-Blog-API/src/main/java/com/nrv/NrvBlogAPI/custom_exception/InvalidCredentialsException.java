package com.nrv.NrvBlogAPI.custom_exception;

/**
 * Custom exception to throw when client provides invalid Credentials.
 *
 * @author Nirav Parekh
 * @see java.lang.RuntimeException
 * @since 1.0
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
