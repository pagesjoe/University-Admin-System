package com.joe.universityadminsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Student;

public interface StudentService {
    Student getStudent(int id);
    List<Student> getStudents();
    Student saveStudent(Student student);
    Student updateStudent(Student student, int id);
    void deleteStudent(int id);
    void deleteStudents();
    Set<Course> getEnrolledCourses(int id);
    Student unwrapStudent(Optional<Student> student, int studentId);
    Student addStudentToCourse(int courseId, int studentId);

}