package com.luma.tankdeluxe.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luma.tankdeluxe.service.UserService;

@RestController
@RequestMapping("register")
@CrossOrigin(origins = "http://localhost:5173/")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUserAccount(@RequestBody CreateUserDTO createUserDTO) {
        this.userService.createUser(createUserDTO.username(), createUserDTO.password());
    }

}

record CreateUserDTO(String username, String password) {
}
