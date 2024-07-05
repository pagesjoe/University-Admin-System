package com.joe.universityadminsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.universityadminsystem.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
    
}