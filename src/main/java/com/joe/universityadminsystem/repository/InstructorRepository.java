package com.joe.universityadminsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.universityadminsystem.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

}
