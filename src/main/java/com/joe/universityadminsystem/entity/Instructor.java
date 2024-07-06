package com.joe.universityadminsystem.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joe.universityadminsystem.validation.Email;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @JsonIgnore
    @ManyToMany(mappedBy = "instructors")
    private Set<Course> courses;

   
}
