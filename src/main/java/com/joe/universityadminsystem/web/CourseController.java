package com.joe.universityadminsystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.service.CourseService;
import com.joe.universityadminsystem.service.InstructorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    CourseService courseService;
    InstructorService instructorService;


    //Create
    @GetMapping("/add")
    public String createCourse(Model model) {
        model.addAttribute("course", new Course());
        return "add_course";
    }
    

    @PostMapping("/submitadd")
    public String submitAddCourse(@Valid Course course, BindingResult result) {
        // If validation errors found
        if(result.hasErrors()){
            return "add_course";
        }

        courseService.saveCourse(course);        
        return "redirect:/course/all";
    }



    //Read
    @GetMapping("/all")
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }
    


    //Update
    @GetMapping("/edit")
    public String updateCourse(@RequestParam int id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        return "edit_course";
    }



    @PostMapping("/submitedit")
    public String submitEditCourse(@Valid Course course, BindingResult result) {
        // If validation errors found
        if(result.hasErrors()){
            return "edit_course";
        }

        courseService.saveCourse(course);        
        return "redirect:/course/all";
    }


    //Delete
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
