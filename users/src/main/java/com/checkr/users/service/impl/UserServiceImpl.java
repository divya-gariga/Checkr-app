package com.checkr.users.service.impl;

import com.checkr.users.entity.User;
import com.checkr.users.exception.UserEntityException;
import com.checkr.users.exception.UserNotfoundException;
import com.checkr.users.repository.UserRepository;
import com.checkr.users.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import javax.validation.Validator;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private JavaMailSender javaMailSender;


    @Autowired
    private Validator validator;

    @Override
    public String registerUser(User user) {
        userRepository.save(user);
      return "registered user";
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        if(result.isPresent()){
            return userRepository.findByEmail(email);
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
            Random random = new Random();
            int randomNumber = random.nextInt(10000);
            otp  = String.format("%04d", randomNumber);
            return otp;
        }
        else {
            throw new UserNotfoundException("User not found with the given email");
        }
//        Optional<User> user = userRepository.findByEmail(email);
//        log.info(String.valueOf(user.isPresent()));
//        if(user.isPresent()){
//            log.info("User entity found by email");
//            log.trace("request received at send verification code ");
//            Random random = new Random();
//            int randomNumber = random.nextInt(10000);
//            String body = String.format("%04d", randomNumber);
//            System.out.println("Random number: " + body);
//            try {
//                MimeMessage message = javaMailSender.createMimeMessage();
//                MimeMessageHelper helper = new MimeMessageHelper(message, true);
//                helper.setFrom("divdiya20@gmail.com");
//                helper.setTo(email);
//                helper.setSubject("Your One-Time Password");
//                helper.setText("Your OTP is " + body);
//                javaMailSender.send(message);

//                MimeMessage mimeMessage = mailSender.createMimeMessage();
//                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
//                messageHelper.setTo(user.get().getEmail());
//                messageHelper.setSubject("OTP for Login");
//                String otp = body;
//                messageHelper.setText("Your OTP is: " + otp, false);
//                mailSender.send(mimeMessage);
//                User usr = user.get();
//                return body;
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    @Override
    public User updateUser( int userId,User user) {
        Optional<User> result = userRepository.findById(userId);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        if(result.isPresent()) {
//            User usr= result.get();
//            usr.setPassword(user.getPassword());
            userRepository.save(user);
            return user;
        }
        else {
            throw new UserNotfoundException("User not found with the given id");
        }
    }

    @Override
    public Optional<User> findByUserId(int userId) {
        log.trace("request received at findById method in service layer");
        return userRepository.findById(userId);
    }
}
