package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.DuplicateGradeException;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.exception.StudentNotEnrolledException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    
    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    CourseRepository courseRepository;
    StudentService studentService;
    CourseService courseService;


    @Override
    public Grade getGrade(int studentId, int courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return unwrapGrade(grade, studentId, courseId);
    }



    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }



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

        //Try saving the grade, or throw duplicate grade exception
        try{
                grade = gradeRepository.save(grade);
            }catch(Exception e){
                throw new DuplicateGradeException(studentId, courseId);
            }

        return grade;
    }



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

}
