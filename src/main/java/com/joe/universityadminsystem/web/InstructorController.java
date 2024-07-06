package com.joe.universityadminsystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joe.universityadminsystem.entity.Instructor;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.service.CourseService;
import com.joe.universityadminsystem.service.InstructorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/instructor")
@AllArgsConstructor
public class InstructorController {

    InstructorService instructorService;
    CourseService courseService;


    //Create
    @GetMapping("/add")
    public String addInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "add_instructor";
    }
    

    @PostMapping("/submitadd")
    public String submitAddInstructor(@Valid Instructor instructor, BindingResult result) {
        // If validation errors found
        if(result.hasErrors()){
            return "add_instructor";
        }

        //Save the instructor
        instructorService.saveInstructor(instructor);
        return "redirect:/instructor/all";
    }


    
    //Read
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


    @PostMapping("/submitedit")
    public String submitEditInstructor(@Valid Instructor instructor, BindingResult result) {
        // If validation errors found
        if(result.hasErrors()){
            return "edit_instructor";
        }

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
