package com.joe.universityadminsystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joe.universityadminsystem.entity.User;
import com.joe.universityadminsystem.repository.AuthorityRepository;
import com.joe.universityadminsystem.repository.UserRepository;
import com.joe.universityadminsystem.service.AuthorityService;
import com.joe.universityadminsystem.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
public class UserApiController {

    UserService userService;
    UserRepository userRepository;

    AuthorityService authorityService;
    AuthorityRepository authorityRepository;


    @PostMapping("/api/register")
    @Operation(summary = "Register user")
    public ResponseEntity<Object> postRegister(@Valid @RequestBody User user,BindingResult result) {
        
        // If validation errors found
        if(result.hasErrors()){
            return new ResponseEntity<Object>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);        
        }

        //If username already exists
        if(userService.existsByUsername(user)){
            return new ResponseEntity<Object>("Username already exists", HttpStatus.BAD_REQUEST);        
        }


        //Register user
        userService.registerUser(user);

        return new ResponseEntity<Object>(user, HttpStatus.CREATED);
    }
    
    
}
