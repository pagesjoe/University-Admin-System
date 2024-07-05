package com.ltp.gradesubmission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Authority;
import com.ltp.gradesubmission.entity.AuthorityId;
import com.ltp.gradesubmission.repository.AuthorityRepository;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public Authority saveAuthority(Authority authority){
        return authorityRepository.save(authority);
    }



    public Authority setAuthority(String role, String username) {
        Authority authority = new Authority();
        AuthorityId authorityId = new AuthorityId(username, "ROLE_" + role);
        authority.setAuthorityId(authorityId);
        return authority;
    }

}
