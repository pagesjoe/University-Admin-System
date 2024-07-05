package com.joe.universityadminsystem.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Instructor;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.service.InstructorService;

import lombok.AllArgsConstructor;

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
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/instructor")
@AllArgsConstructor
public class InstructorApiController {

    InstructorService instructorService;

    @PostMapping
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.saveInstructor(instructor), HttpStatus.CREATED);

    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable int id) {
        return new ResponseEntity<>(instructorService.getInstructor(id), HttpStatus.CREATED);
    }
    


    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getInstructors() {
        return new ResponseEntity<>(instructorService.getInstructors(), HttpStatus.OK);
    }



     @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable int id, @RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.updateInstructor(instructor, id), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInstructor(@PathVariable int id) {
        instructorService.deleteInstructor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteCourses(){
        instructorService.deleteInstructors();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/{id}/courses")
    public ResponseEntity<Set<Course>> getAssignedCourses(@PathVariable int id) {
        return new ResponseEntity<>(instructorService.getAssignedCourses(id), HttpStatus.OK);
    }
}
