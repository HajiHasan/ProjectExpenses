package com.example.projectexpenses.security;

import com.example.projectexpenses.dtos.request.UserDto;
import com.example.projectexpenses.mapper.UserMapper;
import com.example.projectexpenses.model.User;
import com.example.projectexpenses.repository.UserRepository;
import com.example.projectexpenses.serviceimpl.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() ->
                new UsernameNotFoundException(username));
        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        return new MyUserDetails(username, user.getPassword(), roles);
    }
}
