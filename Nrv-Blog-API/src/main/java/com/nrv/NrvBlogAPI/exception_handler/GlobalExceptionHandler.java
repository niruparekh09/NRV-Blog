package com.nrv.NrvBlogAPI.exception_handler;

import com.nrv.NrvBlogAPI.custom_exception.AccessDeniedException;
import com.nrv.NrvBlogAPI.custom_exception.AlreadyExistsException;
import com.nrv.NrvBlogAPI.custom_exception.InvalidCredentialsException;
import com.nrv.NrvBlogAPI.custom_exception.ResourceNotFoundException;
import com.nrv.NrvBlogAPI.dto.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to handle any exception thrown in our server. This is a global level so it will
 * handle all exceptions thrown at any level. It can also handle custom exceptions
 * {@link com.nrv.NrvBlogAPI.custom_exception}
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Component
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<APIResponse> handleTypeMismatch(TypeMismatchException ex) {
        logger.error("Invalid UUID format");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Invalid UUID format"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse(errors.toString()));
    }

    /* Custom Exceptions Handled */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(InvalidCredentialsException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse(ex.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExists(AlreadyExistsException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse(ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new APIResponse(ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(ex.getMessage()));
    }

    /* All other exception */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException
    (Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(e.getMessage()));
    }

}
