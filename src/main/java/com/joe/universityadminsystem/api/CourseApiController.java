package com.joe.universityadminsystem.api;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Instructor;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.service.CourseService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseApiController {

    CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course course) {
        return new ResponseEntity<>(courseService.updateCourse(course, id), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteCourses(){
        courseService.deleteCourses();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable int id) {
        return new ResponseEntity<Set<Student>>(courseService.getEnrolledStudents(id), HttpStatus.OK);
    }
    

    @GetMapping("/{id}/instructors")
    public ResponseEntity<Set<Instructor>> getAssignedInstructors(@PathVariable int id) {
        return new ResponseEntity<Set<Instructor>>(courseService.getAssignedInstructors(id), HttpStatus.OK);
    }



    @PutMapping("/{id}/instructor/{instructorId}")
    public ResponseEntity<Course> AssignInstructorToCourse(@PathVariable int id, @PathVariable int instructorId) {
        
        return new ResponseEntity<>(courseService.AssignInstructorToCourse(instructorId, id), HttpStatus.OK);
    }
}
