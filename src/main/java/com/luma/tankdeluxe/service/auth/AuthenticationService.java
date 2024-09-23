package com.luma.tankdeluxe.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.luma.tankdeluxe.dto.jwt.JwtAuthenticationResponse;
import com.luma.tankdeluxe.entity.User;
import com.luma.tankdeluxe.exception.user.UsernameAlreadyUsedException;
import com.luma.tankdeluxe.service.user.UserService;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(String login, String password) {

        this.checkLogin(login);

        User user = this.userService.create(login, password);

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private void checkLogin(String login) {
        if (login.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid username");
        } else if (this.userService.find(login) != null) {
            throw new UsernameAlreadyUsedException();
        }

    }

    public JwtAuthenticationResponse signin(String login, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password));
        User user = this.userService.find(login);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
