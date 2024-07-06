package com.joe.universityadminsystem.web;

import java.net.http.HttpRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.joe.universityadminsystem.entity.User;
import com.joe.universityadminsystem.repository.AuthorityRepository;
import com.joe.universityadminsystem.service.AuthorityService;
import com.joe.universityadminsystem.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
    public String postRegister(@Valid User user, BindingResult result, Model model) {
        
        // If validation errors found
        if(result.hasErrors()){
            return "register";
        }

        // If username already exists
        if(userService.existsByUsername(user)){
            model.addAttribute("usernameExists", true);
            return "register";
        }

        //Register user
        userService.registerUser(user);

        return "redirect:/user/login";
    }
}
