package com.avb.mobileauth.exception;

import com.avb.mobileauth.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(
            Exception ex,
            WebRequest request) {
        ApiResponse<String> errorResponse = new ApiResponse<>(
            "ERR_INTERNAL_ERROR",
            "An unexpected error occurred"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
