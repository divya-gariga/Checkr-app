package com.checkr.users.controller;

import com.checkr.users.entity.User;
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
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/users")
@Validated

public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@Email @NotBlank @PathVariable String email){
        return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public User updateUser(@Valid @PathVariable int id,@Valid @RequestBody User user){
        return userService.updateUser(id,user);
    }
    @GetMapping("/otp/{email}")
    public String sendVerificationCode(@NotBlank @Email @PathVariable String email) {
        log.info(email);
        log.trace("request received at sendVerificationCode");
        return userService.sendVerificationCode(email);
    }

}