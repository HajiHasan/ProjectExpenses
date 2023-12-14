package com.example.projectexpenses.provider;

import com.example.projectexpenses.dtos.request.AuthenticationRequest;
import com.example.projectexpenses.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final AuthenticationRequest authenticationRequest = (AuthenticationRequest) authentication.getPrincipal();
        String username = authenticationRequest.getEmail();
        UserDetails user = myUserDetailsService.loadUserByUsername(username);
        return createSuccessfulAuthentication(authentication, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,
                user.getPassword(), user.getAuthorities());
        final String rawPassword = (String) authentication.getCredentials();
        boolean isAuthenticated = passwordEncoder.matches(rawPassword, user.getPassword());
        if (!isAuthenticated){
            throw  new BadCredentialsException("password not correct");

        }
        token.setDetails(authentication.getDetails());
        return token;
    }

}
