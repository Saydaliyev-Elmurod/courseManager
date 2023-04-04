package com.example.course.exp;

public class NotValidException extends RuntimeException{
    public NotValidException() {
        super();
    }

    public NotValidException(String message) {
        super(message);
    }
}
