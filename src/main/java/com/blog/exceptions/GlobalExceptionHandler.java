package com.blog.exceptions;

import com.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> responseNotFoundExceptionHandler(ResourceNotFoundException exception) {

        String message = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> map = new HashMap<String, String>();
        exception.getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError)objectError).getField();
            String message = objectError.getDefaultMessage();
            map.put(fieldName, message);
        });
//        exception.getBindingResult().getAllErrors().forEach(objectError -> {
//            String fieldName = ((FieldError)objectError).getField();
//            String message = objectError.getDefaultMessage();
//            map.put(fieldName, message);
//        });
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
