package com.joe.universityadminsystem.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Grade;
import com.joe.universityadminsystem.entity.Student;

public interface GradeService {
    Grade getGrade(int studentId, int courseId);
    Grade saveGrade(Grade grade, int studentId, int courseId);
    Grade updateGrade(String score, int studentId, int courseId);
    void deleteGrade(int studentId, int courseId);
    List<Grade> getStudentGrades(int studentId);
    List<Grade> getCourseGrades(int courseId);
    List<Grade> getAllGrades();
    boolean gradeExists(Course course, Student student);
    Set<Course> getStudentUngradedCourses(int id);

}
