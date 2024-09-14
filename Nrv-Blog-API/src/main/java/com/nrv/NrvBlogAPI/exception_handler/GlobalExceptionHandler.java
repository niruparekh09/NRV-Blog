package com.nrv.NrvBlogAPI.exception_handler;

import com.nrv.NrvBlogAPI.custom_exception.ResourceNotFoundException;
import com.nrv.NrvBlogAPI.dto.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<APIResponse> handleTypeMismatch(TypeMismatchException ex) {
        logger.error("Invalid UUID format");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Invalid UUID format"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleMotoGPException
            (ResourceNotFoundException e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException
            (Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(e.getMessage()));
    }
}
