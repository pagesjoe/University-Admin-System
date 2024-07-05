package com.ltp.gradesubmission.exception;

public class StudentNotEnrolledException extends RuntimeException{

    public StudentNotEnrolledException(int studentId, int courseId){
        super("Student: " + studentId + " is not enrolled to course: " + courseId);
    }
}
