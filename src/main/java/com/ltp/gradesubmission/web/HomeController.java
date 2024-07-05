package com.ltp.gradesubmission.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("")
    public String getHome() {
        return "redirect:/student/all";
    }
    
}
