package com.example.projectexpenses.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails extends User {


    public MyUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities){
        super(email, password, authorities);
    }

}
