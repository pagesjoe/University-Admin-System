package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Instructor;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.exception.DuplicateCourseCodeException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    InstructorService instructorService;


    @Override
    public Course saveCourse(Course course) {
        try{
            course = courseRepository.save(course);
        }catch(Exception e){
            throw new DuplicateCourseCodeException(course.getCode());
        }
        return course;
    }



    @Override
    public Course getCourse(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course, id);
    }



    @Override
    public Course updateCourse(Course course, int id) {
        //Get course from database
        Course unwrappedCourse = getCourse(id);

        //Update course
        unwrappedCourse.setCode(course.getCode());
        unwrappedCourse.setDescription(course.getDescription());
        unwrappedCourse.setSubject(course.getSubject());

        //Save course
        return courseRepository.save(unwrappedCourse);
    }



    @Override
    public void deleteCourse(int id) {  
        courseRepository.deleteById(id);      
    }


    
    @Override
    public void deleteCourses() {
        courseRepository.deleteAll();        
    }



    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }



    @Override
    public Set<Student> getEnrolledStudents(int courseId) {
        Course course = getCourse(courseId);
        return course.getStudents();
    }



    @Override
    public Set<Instructor> getAssignedInstructors(int id) {
        Course course = unwrapCourse(courseRepository.findById(id), id);
        return course.getInstructors();
    }



    @Override
    public Course unwrapCourse(Optional<Course> course, int courseId) {
        if(course.isPresent()){
            return course.get();
        }
        throw new CourseNotFoundException(courseId);
    }



    @Override
    public Course AssignInstructorToCourse(int instructorId, int courseId) {
        Instructor instructor = instructorService.getInstructor(instructorId);
        Course course = unwrapCourse(courseRepository.findById(courseId), courseId);

        //Add Instructor to the set
        course.getInstructors().add(instructor);

        //Save the course
        return courseRepository.save(course);
    }


    
}
