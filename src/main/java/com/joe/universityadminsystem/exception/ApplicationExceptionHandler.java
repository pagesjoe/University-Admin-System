package com.joe.universityadminsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    //Handling not found exception
    @ExceptionHandler({CourseNotFoundException.class, GradeNotFoundException.class, 
        StudentNotFoundException.class, InstructorNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException e){
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    //Handling Bad requests
    @ExceptionHandler({DuplicateCourseCodeException.class, DuplicateGradeException.class,
    StudentNotEnrolledException.class})
    public ResponseEntity<Object> handleDuplicateException(RuntimeException e){
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
