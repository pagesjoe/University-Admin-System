package com.joe.universityadminsystem.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ScoreValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Score {

    String message() default "Invalid data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
