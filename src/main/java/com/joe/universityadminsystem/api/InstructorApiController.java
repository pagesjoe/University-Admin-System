package com.joe.universityadminsystem.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Instructor;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.service.InstructorService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/instructor")
@AllArgsConstructor
public class InstructorApiController {

    InstructorService instructorService;


    //Create
    @PostMapping
    @Operation(summary = "Create instructor")
    public ResponseEntity<Object> saveInstructor(@Valid @RequestBody Instructor instructor, BindingResult result) {
         // If validation errors found
         if(result.hasErrors()){
            return new ResponseEntity<Object>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);        
        }

        return new ResponseEntity<>(instructorService.saveInstructor(instructor), HttpStatus.CREATED);

    }
    

    //Read
    @GetMapping("/{id}")
    @Operation(summary = "Get instructor by id")
    public ResponseEntity<Instructor> getInstructor(@PathVariable int id) {
        return new ResponseEntity<>(instructorService.getInstructor(id), HttpStatus.CREATED);
    }
    


    @GetMapping("/all")
    @Operation(summary = "Get all instructors")
    public ResponseEntity<List<Instructor>> getInstructors() {
        return new ResponseEntity<>(instructorService.getInstructors(), HttpStatus.OK);
    }



    //Update
     @PutMapping("/{id}")
     @Operation(summary = "Update instructor by id")
    public ResponseEntity<Object> updateInstructor(@PathVariable int id,@Valid @RequestBody Instructor instructor,
        BindingResult result) {

             // If validation errors found
             if(result.hasErrors()){
                return new ResponseEntity<Object>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);        
            }

            return new ResponseEntity<>(instructorService.updateInstructor(instructor, id), HttpStatus.OK);
    }



    //Delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete instructor by id")
    public ResponseEntity<HttpStatus> deleteInstructor(@PathVariable int id) {
        instructorService.deleteInstructor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/all")
    @Operation(summary = "Delete all instructors")
    public ResponseEntity<HttpStatus> deleteInstructors(){
        instructorService.deleteInstructors();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/{id}/courses")
    @Operation(summary = "Get assigned courses by instructor id")
    public ResponseEntity<Set<Course>> getAssignedCourses(@PathVariable int id) {
        return new ResponseEntity<>(instructorService.getAssignedCourses(id), HttpStatus.OK);
    }
}
