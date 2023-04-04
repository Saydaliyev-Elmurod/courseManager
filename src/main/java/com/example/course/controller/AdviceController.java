package com.example.course.controller;

import com.example.course.exp.NotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<?> handlerException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
