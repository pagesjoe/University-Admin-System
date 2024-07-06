package com.joe.universityadminsystem.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joe.universityadminsystem.entity.User;
import com.joe.universityadminsystem.repository.AuthorityRepository;
import com.joe.universityadminsystem.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    AuthorityService authorityService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    

    //Checks if there is user logged in
    public boolean loggedin(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getName() != "anonymousUser"){
            return true;
        }
        return false;
    }



    public void registerUser(User user){
        //Encrypt the password
        encryptPassword(user);

        //Set enabled to 1
        user.setEnabled(true);

        //Save the user
        userRepository.save(user);

        //Set the authority and save it
        authorityService.saveAuthority(authorityService.setAuthority("ADMIN", user.getUsername()));
    }



    public boolean existsByUsername(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }

    public void encryptPassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }


}
