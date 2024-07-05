package com.ltp.gradesubmission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltp.gradesubmission.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

}
