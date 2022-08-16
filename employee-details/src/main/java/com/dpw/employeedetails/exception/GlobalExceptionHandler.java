package com.dpw.employeedetails.exception;

import com.dpw.employeedetails.exception.NotFoundException;
import com.dpw.employeedetails.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors = new HashMap<>();
        ex.getAllErrors().forEach(error->errors.put(((FieldError)error).getField(),error.getDefaultMessage()));
        ex.getBindingResult().getAllErrors().forEach(error->errors.put(((FieldError)error).getField(),error.getDefaultMessage()));
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleValidateException(ConstraintViolationException ex)
    {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex){
        return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
