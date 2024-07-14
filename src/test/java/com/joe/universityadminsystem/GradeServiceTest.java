package com.joe.universityadminsystem;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Grade;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.exception.StudentNotEnrolledException;
import com.joe.universityadminsystem.repository.CourseRepository;
import com.joe.universityadminsystem.repository.GradeRepository;
import com.joe.universityadminsystem.repository.StudentRepository;
import com.joe.universityadminsystem.service.CourseService;
import com.joe.universityadminsystem.service.GradeService;
import com.joe.universityadminsystem.service.GradeServiceImpl;
import com.joe.universityadminsystem.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    @Mock
    GradeRepository gradeRepository;
    @Mock
    GradeService gradeService;
    @Mock
    StudentRepository studentRepository;
    @Mock
    CourseRepository courseRepository;
    @Mock
    StudentService studentService;
    @Mock
    CourseService courseService;

    @InjectMocks
    GradeServiceImpl gradeServiceImpl;


    @Test
    public void saveGradeTest(){
        //Arrange
        
        Course course = Course.builder().id(1).build();
        Optional courseOptional = Optional.of(course);
        when(courseRepository.findById(1)).thenReturn(courseOptional);
        when(courseService.unwrapCourse(courseOptional, 1)).thenReturn((Course) courseOptional.get());

        Student student = new Student();
        student.setId(1);
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        student.setCourses(courses);
        Optional studentOptional = Optional.of(student);
        when(studentRepository.findById(1)).thenReturn(studentOptional);
        when(studentService.unwrapStudent(studentOptional, 1)).thenReturn((Student) studentOptional.get());
        
        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> {
            Grade grade = invocation.getArgument(0);
            return grade;
        });

        //Act
        Grade grade = new Grade();
        grade.setScore("B-");
        Grade returnGrade = gradeServiceImpl.saveGrade(grade, 1, 1);

        //Assert
        Grade assertGrade = new Grade();
        assertGrade.setCourse(course);
        assertGrade.setStudent(student);
        assertGrade.setScore("B-");
        assertEquals(assertGrade, returnGrade);
    }


    @Test(expected = StudentNotEnrolledException.class)
    public void throwExceptionIfStudentNotEnrolled(){
        //Arrange
        Course course = new Course();
        course.setId(1);
        Optional courseOptional = Optional.of(course);
        when(courseRepository.findById(1)).thenReturn(courseOptional);
        when(courseService.unwrapCourse(courseOptional, 1)).thenReturn((Course) courseOptional.get());

        Student student = new Student();
        student.setId(1);
        student.setCourses(new HashSet<>());
        Optional studentOptional = Optional.of(student);
        when(studentRepository.findById(1)).thenReturn(studentOptional);
        when(studentService.unwrapStudent(studentOptional, 1)).thenReturn((Student) studentOptional.get());
        
        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> {
            Grade grade = invocation.getArgument(0);
            return grade;
        });

        //Act
        Grade grade = new Grade();
        grade.setScore("B-");

        //Assert
        gradeServiceImpl.saveGrade(grade, 1, 1);
    }





    @Test
    public void updateGradeTest(){
        //Arrange
        Optional<Grade> optional = Optional.of(new Grade(1, "A+", new Student(), new Course()));

        when(gradeRepository.findByStudentIdAndCourseId(1, 1)).thenReturn(optional);

        when(gradeService.unwrapGrade(optional, 1, 1)).thenReturn(optional.get());

        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> {
            Grade grade = invocation.getArgument(0);
            return grade;
        });
        
        //Act
        Grade grade = gradeServiceImpl.updateGrade("B+", 1, 1);

        //Assert
        assertEquals("B+", grade.getScore());
    }



    @Test
    public void getStudentUngradedCoursesTest(){

    }
}
