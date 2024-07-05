package com.joe.universityadminsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Student;

public interface StudentService {
    Student getStudent(int id);
    Student saveStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudents();
    Set<Course> getEnrolledCourses(int id);
    Student unwrapStudent(Optional<Student> student, int studentId);
    void deleteStudents();
    Student updateStudent(Student student, int id);
    Student addStudentToCourse(int courseId, int studentId);

}