package com.checkr.users.service;

import com.checkr.users.entity.User;

import java.util.Optional;

public interface UserService {

    String registerUser(User user);
    Optional<User> findByEmail(String email);
    String sendVerificationCode(String email);
    User updateUser(int userId,User user);
    Optional<User> findByUserId(int userId);

}
