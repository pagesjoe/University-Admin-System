package com.joe.universityadminsystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joe.universityadminsystem.entity.Grade;
import com.joe.universityadminsystem.service.GradeService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("grade")
@AllArgsConstructor
public class GradeController {

    GradeService gradeService;

    //Update grade
    @GetMapping("/edit")
    public String editGrade(@RequestParam int studentId,@RequestParam int courseId, Model model) {
        //Get grade
        Grade grade = gradeService.getGrade(studentId, courseId);

        model.addAttribute("grade", grade);
        model.addAttribute("scores", Grade.scores);
        return "edit_grade";
    }



    //Submit update
    @PostMapping("/submitupdate")
    public String submitUpdateGrade(Grade grade, @RequestParam int studentId, @RequestParam int courseId, 
        RedirectAttributes redirectAttributes) {

            //Save grade
            gradeService.updateGrade(grade.getScore(), studentId, courseId);

            redirectAttributes.addAttribute("id", studentId);
            return "redirect:/student/grades";
    }
    

    
}
