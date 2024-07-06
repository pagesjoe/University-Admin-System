package com.joe.universityadminsystem.api;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseApiController {

    CourseService courseService;

    //Read
    @Operation(summary = "Read course by id")
    // @ApiResponse(responseCode = "404", description = "Doesn't exist", content = @Content(schema = @Schema(implementation = String.class)))
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }


    @GetMapping("/all")
    @Operation(summary = "Read all courses")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }



    //Create
    @PostMapping
    @Operation(summary = "Create course")
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid Course course, BindingResult result) {
        // If validation errors found
        if(result.hasErrors()){
            return new ResponseEntity<Object>(
                result.getAllErrors().get(0).getDefaultMessage(), 
                HttpStatus.BAD_REQUEST);        
        }

            return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }


    //Update
    @PutMapping("/{id}")
    @Operation(summary = "update course by id")
    public ResponseEntity<Object> updateCourse(@PathVariable int id,@Valid @RequestBody Course course, 
        BindingResult result) {
            // If validation errors found
            if(result.hasErrors()){
                return new ResponseEntity<Object>(
                    result.getAllErrors().get(0).getDefaultMessage(), 
                    HttpStatus.BAD_REQUEST);        
            }

            return new ResponseEntity<>(courseService.updateCourse(course, id), HttpStatus.OK);
    }



    //Delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course by id")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/all")
    @Operation(summary = "Delete all courses")
    public ResponseEntity<HttpStatus> deleteCourses(){
        courseService.deleteCourses();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    @GetMapping("/{id}/students")
    @Operation(summary = "Get enrolled students by course id")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable int id) {
        return new ResponseEntity<Set<Student>>(courseService.getEnrolledStudents(id), HttpStatus.OK);
    }
    

    @GetMapping("/{id}/instructors")
    @Operation(summary = "Get assigned instructors by course id")
    public ResponseEntity<Set<Instructor>> getAssignedInstructors(@PathVariable int id) {
        return new ResponseEntity<Set<Instructor>>(courseService.getAssignedInstructors(id), HttpStatus.OK);
    }



    @PutMapping("/{courseId}/instructor/{instructorId}")
    @Operation(summary = "Assign instructor to course by course id and instructor id")
    public ResponseEntity<Course> AssignInstructorToCourse(@PathVariable int courseId, @PathVariable int instructorId) {
        
        return new ResponseEntity<>(courseService.AssignInstructorToCourse(instructorId, courseId), HttpStatus.OK);
    }
}
