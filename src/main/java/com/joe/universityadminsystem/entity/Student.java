package com.joe.universityadminsystem.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name ="course_student",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<Course> courses;

}
