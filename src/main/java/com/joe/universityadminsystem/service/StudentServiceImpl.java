package com.joe.universityadminsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.exception.StudentNotFoundException;
import com.joe.universityadminsystem.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    CourseService courseService;

    @Override
    public Student getStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student, id);
    }



    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


    

    @Override
    public Student updateStudent(Student student, int id) {
        Student unwrappedStudent = unwrapStudent(studentRepository.findById(id), id);

        unwrappedStudent.setName(student.getName());
        unwrappedStudent.setBirthdate(student.getBirthdate());

        return studentRepository.save(unwrappedStudent);
    }



    @Override
    public void deleteStudent(int id) { 
        studentRepository.deleteById(id);       
    }

    

    @Override
    public void deleteStudents() {
        studentRepository.deleteAll();        
    }



    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    @Override
    public Student unwrapStudent(Optional<Student> student, int studentId){
        if(student.isPresent()){
            return student.get();
        }
        throw new StudentNotFoundException(studentId);
    }

    @Override
    public Set<Course> getEnrolledCourses(int id) {
        Student student = getStudent(id);
        return student.getCourses();
    }



    @Override
    public Student addStudentToCourse(int courseId, int studentId) {
        //Get course by id
        Course course = courseService.getCourse(courseId);

        //Get student by id
        Optional<Student> student = studentRepository.findById(studentId);
        Student unwrappedStudent = unwrapStudent(student, studentId);

        //Add student to course students list
        unwrappedStudent.getCourses().add(course);

        //Save Student
        return studentRepository.save(unwrappedStudent);
    }
}