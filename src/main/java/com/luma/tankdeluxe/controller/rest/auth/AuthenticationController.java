package com.luma.tankdeluxe.controller.rest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luma.tankdeluxe.dto.jwt.JwtAuthenticationResponse;
import com.luma.tankdeluxe.service.AuthenticationService;

@RestController
@RequestMapping
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("sign-up")
    public JwtAuthenticationResponse createUserAccount(@RequestBody UserInfo dto) {
        return this.authService.signup(dto.username(), dto.password());
    }

    @PostMapping("sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody UserInfo dto) {
        return this.authService.signin(dto.username(), dto.password());
    }

}

record UserInfo(String username, String password) {
}
