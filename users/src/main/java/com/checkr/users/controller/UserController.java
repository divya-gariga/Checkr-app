package com.checkr.users.controller;

import com.checkr.users.dto.UserCreationDTO;
import com.checkr.users.dto.UserDTO;
import com.checkr.users.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@Slf4j
@RequestMapping("/api/users")
@Validated

public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserCreationDTO userCreationDTO){
        return new ResponseEntity<>(userService.registerUser(userCreationDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@Email @NotBlank @PathVariable String email){
        return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public UserCreationDTO updateUser(@Valid @PathVariable int id,@Valid @RequestBody UserCreationDTO userCreationDTO){
        return userService.updateUser(id,userCreationDTO);
    }
    @GetMapping("/otp/{email}")
    public String sendVerificationCode(@NotBlank @Email @PathVariable String email) {
        return userService.sendVerificationCode(email);
    }



}