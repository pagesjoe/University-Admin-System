package com.joe.universityadminsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.universityadminsystem.entity.Authority;
import com.joe.universityadminsystem.entity.AuthorityId;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId>{

}
