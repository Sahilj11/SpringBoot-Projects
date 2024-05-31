package com.sahil.fileupload.globalexception;

import com.sahil.fileupload.customexception.PasswordNotMatchException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** MyControllerAdvice */
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<String> handlePasswordMatching(PasswordNotMatchException pException) {
        return new ResponseEntity<String>("Password do not match", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValid(MethodArgumentNotValidException pException) {
        Map<String,String> mp = new HashMap<>();
        pException.getBindingResult().getAllErrors().forEach((error)->{
            String fieldname = (( FieldError )error).getField();
            String message = error.getDefaultMessage();
            mp.put(fieldname, message);
        });
        return new ResponseEntity<Map<String,String>>(mp,HttpStatus.BAD_REQUEST);
    }

}
