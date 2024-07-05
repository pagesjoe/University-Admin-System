package com.ltp.gradesubmission.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.CourseService;
import com.ltp.gradesubmission.service.GradeService;
import com.ltp.gradesubmission.service.StudentService;

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
    public String addStudent() {
        return "add_student";
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

    @PostMapping("/submit")
    public String submitStudent(Student student) {
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
        studentService.addStudentToCourse(courseId, studentId); 
        redirectAttributes.addAttribute("id", studentId);
        return "redirect:/student/courses";
    }
    


    @GetMapping("/addgrade")
    public String addGrade(@RequestParam int id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("courses", studentService.getEnrolledCourses(id));
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
