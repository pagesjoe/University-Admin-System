package com.joe.universityadminsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.universityadminsystem.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

    boolean existsByUsername(String username);

}
