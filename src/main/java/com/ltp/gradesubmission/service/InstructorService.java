package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Instructor;
import com.ltp.gradesubmission.entity.Student;

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
