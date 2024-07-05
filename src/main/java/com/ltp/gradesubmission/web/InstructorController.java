package com.ltp.gradesubmission.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltp.gradesubmission.entity.Instructor;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.service.CourseService;
import com.ltp.gradesubmission.service.InstructorService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/instructor")
@AllArgsConstructor
public class InstructorController {

    InstructorService instructorService;
    CourseService courseService;

    @GetMapping("/add")
    public String addInstructor() {
        return "add_instructor";
    }
    

    @GetMapping("/all")
    public String getInstructors(Model model) {
        model.addAttribute("instructors", instructorService.getInstructors());
        return "instructors";
    }
    
    @GetMapping("/edit")
    public String editStudent(@RequestParam int id, Model model) {
        //Get instructor
        Instructor instructor = instructorService.getInstructor(id);

        model.addAttribute("instructor", instructor);
        return "edit_instructor";
    }

    @PostMapping("/submit")
    public String submitInstructor(Instructor instructor) {
        //Save the instructor
        instructorService.saveInstructor(instructor);
        return "redirect:/instructor/all";
    }
    
    
    @PostMapping("/delete")
    public String deleteInstructor(@RequestParam int id) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructor/all";
    }
    

    @GetMapping("/courses")
    public String getCourses(@RequestParam int id, Model model) {
        model.addAttribute("instructor", instructorService.getInstructor(id));
        model.addAttribute("courses", instructorService.getAssignedCourses(id));
        return "instructor_courses";
    }
    
    
    @GetMapping("/assign")
    public String AssignInstructor(@RequestParam int id, Model model) {
        //get student
        model.addAttribute("instructor", instructorService.getInstructor(id));

        //get courses
        model.addAttribute("courses", courseService.getCourses());
        return "assign_instructor";
    }
    

    @PostMapping("/assign")
    public String submitAssign(@RequestParam int courseId, @RequestParam int instructorId,
        RedirectAttributes redirectAttributes) {
        courseService.AssignInstructorToCourse(instructorId, courseId);
        redirectAttributes.addAttribute("id", instructorId);
        return "redirect:/instructor/courses";
    }
}
