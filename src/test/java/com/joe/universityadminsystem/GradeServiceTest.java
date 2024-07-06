package com.joe.universityadminsystem;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Grade;
import com.joe.universityadminsystem.entity.Student;
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
    // @Mock
    // StudentRepository studentRepository;
    // @Mock
    // CourseRepository courseRepository;
    // @Mock
    // StudentService studentService;
    // @Mock
    // CourseService courseService;

    @InjectMocks
    GradeServiceImpl gradeServiceImpl;

    @Test
    public void updateGradeTest(){
        //Arrange
        Optional<Grade> optional = Optional.of(new Grade(1, "A+", new Student(), new Course()));
        when(gradeRepository.findByStudentIdAndCourseId(1, 1)).thenReturn(optional);
        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> {
            Grade grade = invocation.getArgument(0);
            return grade;
        });
        
        //Act
        Grade grade = gradeServiceImpl.updateGrade("B+", 1, 1);

        //Assert
        assertEquals("B+", grade.getScore());
    }
}
