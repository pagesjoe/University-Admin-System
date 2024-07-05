package com.ltp.gradesubmission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltp.gradesubmission.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

    boolean existsByUsername(String username);

}
