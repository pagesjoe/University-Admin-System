package com.ltp.gradesubmission.web;

import java.net.http.HttpRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.repository.AuthorityRepository;
import com.ltp.gradesubmission.service.AuthorityService;
import com.ltp.gradesubmission.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserService userService;
    AuthorityService authorityService;
    AuthorityRepository authorityRepository;


    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }


    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        //Redirect to home page if user is already logged in
        if(userService.loggedin()){
            return "redirect:/";
        }

        model.addAttribute("user", new User());
        return "register";
    }
    
    


    @PostMapping("/register")
    public String postRegister(User user, @RequestParam String role, Model model) {
        
        // If validation errors found
        // if(result.hasErrors()){
        //     return "register_form";
        // }

        // If username already exists
        if(userService.existsByUsername(user)){
            model.addAttribute("usernameExists", true);
            return "register";
        }

        //If email already exists
        // if(userService.existsByEmail(user.getEmail())){
        //     Boolean emailExists = true;
        //     model.addAttribute("emailExists", emailExists);
        //     return "register_form";
        // }

        //Register user
        userService.registerUser(user, role);

        return "redirect:/user/login";
    }
}
