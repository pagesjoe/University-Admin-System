package com.joe.universityadminsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Instructor;
import com.joe.universityadminsystem.entity.Student;

public interface InstructorService {

    Instructor saveInstructor(Instructor instructor);
    Instructor getInstructor(int id);
    List<Instructor> getInstructors();
    Instructor updateInstructor(Instructor instructor, int id);
    void deleteInstructor(int id);
    void deleteInstructors();
    Set<Course> getAssignedCourses(int id);
    Instructor unwrapInstructor(int instructorId);

}
