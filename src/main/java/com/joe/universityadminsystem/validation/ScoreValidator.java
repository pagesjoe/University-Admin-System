package com.joe.universityadminsystem.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ScoreValidator implements ConstraintValidator<Score, String>{

    List<String> scores = Arrays.asList(
        "A+", "A", "A-",
        "B+", "B", "B-",
        "C+", "C", "C-",
        "D+", "D", "D-",
        "F"
    );


    @Override
    public boolean isValid(String score, ConstraintValidatorContext arg1) {
        for(String value : scores){
            if(score.equals(value)){
                return true;
            }
        }
        
        return false;
    }

    

}
