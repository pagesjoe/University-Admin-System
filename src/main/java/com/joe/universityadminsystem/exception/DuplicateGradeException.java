package com.joe.universityadminsystem.exception;

public class DuplicateGradeException extends RuntimeException{

    public DuplicateGradeException(int studentId, int courseId){
        super("A grade for student: " + studentId + " in course: " + courseId + " already exists");
    }

}
