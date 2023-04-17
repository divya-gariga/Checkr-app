package com.checkr.users.service.impl;

import com.checkr.users.dto.UserCreationDTO;
import com.checkr.users.dto.UserDTO;
import com.checkr.users.entity.User;
import com.checkr.users.exception.UserNotfoundException;
import com.checkr.users.mapper.UserMapper;
import com.checkr.users.repository.UserRepository;
import com.checkr.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import javax.validation.Validator;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private UserMapper userMapper;


    private Random random= new Random();
    @Override
    public String registerUser(UserCreationDTO userCreationDTO) {
        User userEntity= userMapper.convertToUserEntity(userCreationDTO);
        userRepository.save(userEntity);
      return "registered user";
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        if(result.isPresent()){
            return userMapper.convertToUserDTO(result.get());
        }
        else {
            throw new UserNotfoundException("User not found with the given email");
        }
    }

    @Override
    public String sendVerificationCode(String email) {
        String otp="";
        Optional<User> result = userRepository.findByEmail(email);
        if(result.isPresent()){
            int randomNumber = random.nextInt(10000);
            otp  = String.format("%04d", randomNumber);
            return otp;
        }
        else {
            throw new UserNotfoundException("User not found with the given email");
        }
    }

    @Override
    public UserCreationDTO updateUser( int id,UserCreationDTO userCreationDTO) {
        Optional<User> result = userRepository.findById(id);
        User userEntity= userMapper.convertToUserEntity(userCreationDTO);
        Set<ConstraintViolation<User>> violations = validator.validate(userEntity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        if(result.isPresent()) {
            userRepository.save(userEntity);
            return userCreationDTO;
        }
        else {
            throw new UserNotfoundException("User not found with the given id");
        }
    }
}
