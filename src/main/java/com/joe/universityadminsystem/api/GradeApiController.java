package com.joe.universityadminsystem.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.universityadminsystem.entity.Grade;
import com.joe.universityadminsystem.service.GradeService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/grade")
public class GradeApiController {
    
    GradeService gradeService;


    //Read
    @GetMapping("/student/{studentId}/course/{courseId}")
    @Operation(summary = "Get grade by student id and course id")
    public ResponseEntity<Grade> getGrade(@PathVariable int studentId, @PathVariable int courseId) {
        return new ResponseEntity<>(gradeService.getGrade(studentId, courseId), HttpStatus.OK);
    }


    
    @GetMapping("/all")
    @Operation(summary = "Get all grades")
    public ResponseEntity<List<Grade>> getGrades() {
        return new ResponseEntity<>(gradeService.getAllGrades(), HttpStatus.OK);
    }



    //Create
    @PostMapping("/student/{studentId}/course/{courseId}")
    @Operation(summary = "Create grade by student id and course id")
    public ResponseEntity<Object> saveGrade(@Valid @RequestBody Grade grade, BindingResult result,
        @PathVariable int studentId, @PathVariable int courseId) {
             // If validation errors found
             if(result.hasErrors()){
                return new ResponseEntity<Object>(
                    result.getAllErrors().get(0).getDefaultMessage(), 
                    HttpStatus.BAD_REQUEST);        
            }

            return new ResponseEntity<>(gradeService.saveGrade(grade, studentId, courseId), HttpStatus.CREATED);
    }



    //Update
    @PutMapping("/student/{studentId}/course/{courseId}")
    @Operation(summary = "Update grade by student id and course id")
    public ResponseEntity<Object> updateGrade(@Valid @RequestBody Grade grade, BindingResult result, 
        @PathVariable int studentId, @PathVariable int courseId) {
             // If validation errors found
             if(result.hasErrors()){
                return new ResponseEntity<Object>(
                    result.getAllErrors().get(0).getDefaultMessage(), 
                    HttpStatus.BAD_REQUEST);        
            }

            return new ResponseEntity<>(gradeService.updateGrade(grade.getScore(), studentId, courseId), HttpStatus.OK);
    }



    //Delete
    @DeleteMapping("/student/{studentId}/course/{courseId}")
    @Operation(summary = "Delete grade by student id and course id")
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable int studentId, @PathVariable int courseId) {
        gradeService.deleteGrade(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get student grades by student id")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable int studentId) {
        return new ResponseEntity<>(gradeService.getStudentGrades(studentId), HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get course grades by course id")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable int courseId) {
        return new ResponseEntity<>(gradeService.getCourseGrades(courseId), HttpStatus.OK);
    }


}
