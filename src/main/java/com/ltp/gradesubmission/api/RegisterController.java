package com.ltp.gradesubmission.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.repository.AuthorityRepository;
import com.ltp.gradesubmission.repository.UserRepository;
import com.ltp.gradesubmission.service.AuthorityService;
import com.ltp.gradesubmission.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
public class RegisterController {

    UserService userService;
    UserRepository userRepository;

    AuthorityService authorityService;
    AuthorityRepository authorityRepository;


    @PostMapping("/api/register")
    public ResponseEntity<User> postRegister(@RequestBody User user, @RequestParam String role) {
        
        // If validation errors found
        // if(result.hasErrors()){
        //     return "register_form";
        // }

        //If username already exists
        // if(userService.existsByUsername(user.getUsername())){
        //     Boolean usernameExists = true;
        //     model.addAttribute("usernameExists", usernameExists);
        //     return "register_form";
        // }

        //If email already exists
        // if(userService.existsByEmail(user.getEmail())){
        //     Boolean emailExists = true;
        //     model.addAttribute("emailExists", emailExists);
        //     return "register_form";
        // }

        //Encrypt the password
        userService.encryptPassword(user);

        //Set enabled to 1
        user.setEnabled(true);

        //Save the user
        userRepository.save(user);

        //Set the authority and save it
        authorityRepository.save(authorityService.setAuthority(role, user.getUsername()));

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    
    
}
