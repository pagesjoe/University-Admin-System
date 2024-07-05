package com.ltp.gradesubmission.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private String code;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Grade> grades;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    Set<Student> students;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name ="course_instructor",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    Set<Instructor> instructors;
}
