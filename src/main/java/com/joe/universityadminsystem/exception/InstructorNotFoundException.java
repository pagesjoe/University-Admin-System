package com.joe.universityadminsystem.exception;

public class InstructorNotFoundException extends RuntimeException{

    public InstructorNotFoundException(int id){
        super("The instructor id '" + id + "' does not exist in our records");
    }
}
