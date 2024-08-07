package com.joe.universityadminsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Grade;
import com.joe.universityadminsystem.entity.Student;

import jakarta.transaction.Transactional;

public interface GradeRepository extends JpaRepository<Grade, Integer>{


    public Optional<Grade> findByStudentIdAndCourseId(int studentId, int courseId);  
    
    //Has to be transactional because it may involve deleting multiple entries at the same time
    @Transactional
    public void deleteByStudentIdAndCourseId(int studentId, int courseId);

    public List<Grade> findByStudentId(int studentId);

    public List<Grade> findByCourseId(int studentId);

    public boolean existsByCourseAndStudent(Course course, Student student);
    
}