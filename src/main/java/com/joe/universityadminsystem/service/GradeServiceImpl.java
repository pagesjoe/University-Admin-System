package com.joe.universityadminsystem.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Grade;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.exception.DuplicateGradeException;
import com.joe.universityadminsystem.exception.GradeNotFoundException;
import com.joe.universityadminsystem.exception.StudentNotEnrolledException;
import com.joe.universityadminsystem.repository.CourseRepository;
import com.joe.universityadminsystem.repository.GradeRepository;
import com.joe.universityadminsystem.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    
    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    CourseRepository courseRepository;
    StudentService studentService;
    CourseService courseService;



    //Create
    @Override
    public Grade saveGrade(Grade grade, int studentId, int courseId) {
        //Get student and course by id
        Student student = studentService.unwrapStudent(studentRepository.findById(studentId), studentId);
        Course course = courseService.unwrapCourse(courseRepository.findById(courseId), courseId);

        //Check if student is not enrolled to the course first
        if(!student.getCourses().contains(course)) throw new StudentNotEnrolledException(studentId, courseId);

        //Set grade's student and course
        grade.setStudent(student);
        grade.setCourse(course);

        //Try saving the grade, or catch duplicate grade exception
        try{
                grade = gradeRepository.save(grade);
            }catch(Exception e){
                throw new DuplicateGradeException(studentId, courseId);
            }

        return grade;
    }



    //Read
    @Override
    public Grade getGrade(int studentId, int courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return unwrapGrade(grade, studentId, courseId);
    }



    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }




    //Update
    @Override
    public Grade updateGrade(String score, int studentId, int courseId) {
        //Get the grade
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        Grade unwrappedGrade = unwrapGrade(grade, studentId, courseId);

        //Update the grade
        unwrappedGrade.setScore(score);
        //Save the grade
        return gradeRepository.save(unwrappedGrade);
        
    }



    //Delete
    @Override
    public void deleteGrade(int studentId, int courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }



    @Override
    public List<Grade> getStudentGrades(int studentId) {
        return gradeRepository.findByStudentId(studentId);
    }



    @Override
    public List<Grade> getCourseGrades(int courseId) {
        return gradeRepository.findByCourseId(courseId);
    }



    //Return optional.get or else throw exception
    public Grade unwrapGrade(Optional<Grade> grade, int studentId, int courseId){
        if(grade.isPresent()){
            return grade.get();
        }
        throw new GradeNotFoundException(studentId, courseId);
    }




    public boolean gradeExists(Course course, Student student){
        return gradeRepository.existsByCourseAndStudent(course, student);
    }



    public Set<Course> getStudentUngradedCourses(int studentId){

        //Get enrolled courses
        Student student = studentService.getStudent(studentId);
        Set<Course> enrolledCourses = student.getCourses();

        //Copying the courses set
        Set<Course> ungradedCourses = new HashSet<>();
        for(Course course : enrolledCourses){
            ungradedCourses.add(course);
        }

        //remove graded courses
        for(Course course : enrolledCourses){
            if(gradeExists(course, student)){
                ungradedCourses.remove(course);
            }
        }

        return ungradedCourses;
    }
}
