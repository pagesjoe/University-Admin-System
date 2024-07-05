package com.ltp.gradesubmission.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.service.CourseService;
import com.ltp.gradesubmission.service.InstructorService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    CourseService courseService;
    InstructorService instructorService;

    @GetMapping("/add")
    public String createCourse() {
        return "add_course";
    }
    

    @GetMapping("/all")
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }
    

    @GetMapping("/edit")
    public String updateCourse(@RequestParam int id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        return "edit_course";
    }



    @PostMapping("/submit")
    public String submitCourse(Course course) {
        courseService.saveCourse(course);        
        return "redirect:/course/all";
    }


    @PostMapping("/delete")
    public String deleteCourse(@RequestParam int id) {
        courseService.deleteCourse(id);        
        return "redirect:/course/all";

    }
    
    @GetMapping("/instructors")
    public String getCourses(@RequestParam int id, Model model) {
        model.addAttribute("instructors", courseService.getAssignedInstructors(id));
        model.addAttribute("course", courseService.getCourse(id));
        return "course_instructors";
    }



    @GetMapping("/assign")
    public String AssignInstructor(@RequestParam int id, Model model) {
        //get course
        model.addAttribute("course", courseService.getCourse(id));

        //get instructors
        model.addAttribute("instructors", instructorService.getInstructors());
        return "assign_course";
    }
    

    
    @PostMapping("/assign")
    public String submitAssign(@RequestParam int courseId, @RequestParam int instructorId,
        RedirectAttributes redirectAttributes) {
        courseService.AssignInstructorToCourse(instructorId, courseId);
        redirectAttributes.addAttribute("id", courseId);
        return "redirect:/course/instructors";
    }
}
