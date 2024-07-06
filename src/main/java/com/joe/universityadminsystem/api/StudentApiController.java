package com.joe.universityadminsystem.api;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
public class StudentApiController {

    StudentService studentService;


    //Create
    @PostMapping
    @Operation(summary = "Create student")
    public ResponseEntity<Object> saveStudent(@Valid @RequestBody Student student, BindingResult result) {
        // If validation errors found
        if(result.hasErrors()){
            return new ResponseEntity<Object>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);        
        }

        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }



    //Read
    @GetMapping("/{id}")
    @Operation(summary = "Get student by id")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }


    @GetMapping("/all")
    @Operation(summary = "Get all students")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }



    
    //Update
    @PutMapping("/{id}")
    @Operation(summary = "Update student by id")
    public ResponseEntity<Object> updateStudent(@PathVariable int id,@Valid @RequestBody Student student, 
        BindingResult result) {
             // If validation errors found
            if(result.hasErrors()){
                return new ResponseEntity<Object>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);        
            }

            return new ResponseEntity<>(studentService.updateStudent(student, id), HttpStatus.OK);
    }



    //Delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student by id")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all students")
    public ResponseEntity<HttpStatus> deleteStudents(){
        studentService.deleteStudents();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    

    @GetMapping("/{id}/courses")
    @Operation(summary = "Get enrolled courses by student id")
    public ResponseEntity<Set<Course>> getEnrolledCourses(@PathVariable int id) {
        return new ResponseEntity<>(studentService.getEnrolledCourses(id), HttpStatus.OK);
    }
    


    @PutMapping("/{studentId}/course/{courseId}")
    @Operation(summary = "Enroll student to course by student id and course id")
    public ResponseEntity<Student> enrollStudentToCourse(@PathVariable int studentId, @PathVariable int courseId){
        return new ResponseEntity<>(studentService.addStudentToCourse(courseId, studentId), HttpStatus.OK);
    }

}
