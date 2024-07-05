package com.ltp.gradesubmission.exception;

public class StudentNotFoundException extends RuntimeException { 

    public StudentNotFoundException(int id) {
        super("The student id '" + id + "' does not exist in our records");
    }
    
}