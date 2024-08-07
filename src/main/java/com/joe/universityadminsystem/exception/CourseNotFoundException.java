package com.joe.universityadminsystem.exception;

public class CourseNotFoundException extends RuntimeException { 

    public CourseNotFoundException(int id) {
        super("The course id '" + id + "' does not exist in our records");
    }
    
}