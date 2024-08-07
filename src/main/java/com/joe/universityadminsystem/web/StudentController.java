package com.joe.universityadminsystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joe.universityadminsystem.entity.Grade;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.repository.GradeRepository;
import com.joe.universityadminsystem.service.CourseService;
import com.joe.universityadminsystem.service.GradeService;
import com.joe.universityadminsystem.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    StudentService studentService;
    CourseService courseService;
    GradeService gradeService;

    //Create
    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "add_student";
    }
    

    @PostMapping("/submitadd")
    public String submitAddStudent(@Valid Student student, BindingResult result) {
        // If validation errors found
        if(result.hasErrors()){
            return "add_student";
        }
        //Save the student
        studentService.saveStudent(student);    
        return "redirect:/student/all";
    }



    //Read
    @GetMapping({"/all", ""})
    public String getstudents(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }
    

    //Update
    @GetMapping("/edit")
    public String editStudent(@RequestParam int id, Model model) {
        Student student = studentService.getStudent(id);

        model.addAttribute("student", student);
        return "edit_student";
    }



    @PostMapping("/submitedit")
    public String submitEditStudent(@Valid Student student, BindingResult result) {
        // If validation errors found
        if(result.hasErrors()){
            return "edit_student";
        }
        //Save the student
        studentService.saveStudent(student);    
        return "redirect:/student/all";
    }
    
    
    //Delete
    @PostMapping("/delete")
    public String deleteStudent(@RequestParam int id) {

        studentService.deleteStudent(id);
        return "redirect:/student/all";
    }
    

    @GetMapping("/courses")
    public String getCourses(@RequestParam int id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("courses", studentService.getEnrolledCourses(id));
        return "student_courses";
    }
    

    @GetMapping("/grades")
    public String getGrades(@RequestParam int id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("grades", gradeService.getStudentGrades(id));
        return "student_grades";
    }
    

    @GetMapping("/enroll")
    public String enrollStudent(@RequestParam int id, Model model) {
        //get student
        model.addAttribute("student", studentService.getStudent(id));

        //get courses
        model.addAttribute("courses", courseService.getCourses());
        return "enroll_student";
    }
    

    @PostMapping("/enroll")
    public String submitEnroll(@RequestParam int courseId, @RequestParam int studentId, 
        RedirectAttributes redirectAttributes) {
            //Enroll student
            studentService.addStudentToCourse(courseId, studentId); 

            redirectAttributes.addAttribute("id", studentId);
            return "redirect:/student/courses";
        }
    


    @GetMapping("/addgrade")
    public String addGrade(@RequestParam int id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("courses", gradeService.getStudentUngradedCourses(id));
        model.addAttribute("scores", Grade.scores);
        return "add_student_grade";
    }


    @PostMapping("/addgrade")
    public String submitGrade(@RequestParam int studentId, @RequestParam int courseId, 
        Grade grade, RedirectAttributes redirectAttributes) {

            gradeService.saveGrade(grade, studentId, courseId);

            redirectAttributes.addAttribute("id", studentId);
            return "redirect:/student/grades";
    }
    
    
}
