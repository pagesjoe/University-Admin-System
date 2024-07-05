package com.joe.universityadminsystem.exception;

public class DuplicateCourseCodeException extends RuntimeException{

    public DuplicateCourseCodeException(String code) {
        super("The course code: " + code + " Already exists");
    }
}
