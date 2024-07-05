package com.ltp.gradesubmission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltp.gradesubmission.entity.Authority;
import com.ltp.gradesubmission.entity.AuthorityId;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId>{

}
