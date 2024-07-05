package com.joe.universityadminsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Instructor;
import com.joe.universityadminsystem.entity.Student;

public interface CourseService {
    Course getCourse(int id);
    List<Course> getCourses();
    Course saveCourse(Course course);
    Course updateCourse(Course course, int id);
    void deleteCourse(int id);
    void deleteCourses();
    Set<Student> getEnrolledStudents(int courseId);
    Course unwrapCourse(Optional<Course> course, int courseId);
    Course AssignInstructorToCourse(int instructorId, int courseId);
    Set<Instructor> getAssignedInstructors(int id);
}