package com.joe.universityadminsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.universityadminsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

    
}