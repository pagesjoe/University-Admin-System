package com.joe.universityadminsystem.entity;

import java.util.Arrays;
import java.util.List;

import com.joe.universityadminsystem.validation.Score;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private int id;

    @NotBlank(message = "Score cannot be empty")
    @Score(message = "Invalid score")
    private String score;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public final static List<String> scores = Arrays.asList(
        "A+", "A", "A-",
        "B+", "B", "B-",
        "C+", "C", "C-",
        "D+", "D", "D-",
        "F"
    );

}
