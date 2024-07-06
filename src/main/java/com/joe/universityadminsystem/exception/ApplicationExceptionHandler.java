package com.joe.universityadminsystem.exception;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    //Handling date error
    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<Object> handleInvalidDate(RuntimeException e){
        return new ResponseEntity<Object>("Invalid Date Format", HttpStatus.BAD_REQUEST);
    }


    //Handling validation errors
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
    //     return new ResponseEntity<Object>(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
    // }

    

}
