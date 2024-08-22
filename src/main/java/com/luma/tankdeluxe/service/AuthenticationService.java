package com.luma.tankdeluxe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luma.tankdeluxe.dto.jwt.JwtAuthenticationResponse;
import com.luma.tankdeluxe.entity.User;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(String login, String password) {
        User user = User.builder().login(login)
                .password(passwordEncoder.encode(password))
                .role("USER").build();
        this.userService.save(user);

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(String login, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password));
        User user = this.userService.find(login);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
