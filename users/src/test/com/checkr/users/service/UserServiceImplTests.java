package com.checkr.users.service;

import com.checkr.users.dto.UserCreationDTO;
import com.checkr.users.dto.UserDTO;
import com.checkr.users.entity.User;
import com.checkr.users.exception.UserNotfoundException;
import com.checkr.users.mapper.UserMapper;
import com.checkr.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import javax.validation.Validator;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.Set;

@SpringBootTest

public class UserServiceImplTests {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    UserMapper userMapper;

    private User user;
    private UserCreationDTO newUser;
    private UserDTO newUserDto;

    @BeforeEach
    void setup(){
        user= new User(1,"tester","tester@gmail.com","9876543217","zemoso@123");
        newUser= new UserCreationDTO(1,"tester","tester@gmail.com","9876543217","zemoso@123");
        newUserDto= new UserDTO(1,"tester","tester@gmail.com","9876543217");

    }

    @Autowired
    private Validator validator;

    @Test
    public void testUpdateUserWithValidationErrors() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User user1 = new User();
        user1.setId(1);
        user1.setName("john");
        user1.setEmail("johndoe@example.com");
        user1.setPhone("950981239");
        UserCreationDTO newUser1 = new UserCreationDTO();
        newUser1.setId(1);
        newUser1.setName("john");
        newUser1.setEmail("johndoe@example.com");
        newUser1.setPhone("950981239");
        when(userMapper.convertToUserEntity(newUser1)).thenReturn(user1);
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        Throwable exception= assertThrows(ConstraintViolationException.class,()->userService.updateUser(1,newUser1));
        assertEquals(("password: must not be null"), exception.getMessage());
    }

    @Test
    void registerUser(){
        UserCreationDTO newUser = new UserCreationDTO(1,"divya","divya@gmail.com","9876543217","zemoso@123");
        User user = new User(1,"divya","divya@gmail.com","9876543217","zemoso@123");
        when(userMapper.convertToUserEntity(newUser)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals("registered user",userService.registerUser(newUser));
    }

    @Test
    void findByEmail(){
        when(userRepository.findByEmail("tester@gmail.com")).thenReturn(Optional.of(user));
        when(userMapper.convertToUserDTO(user)).thenReturn(newUserDto);
        assertEquals(newUserDto,userService.findByEmail("tester@gmail.com"));
    }

    @Test
    void findByEmailWhenUserIsNotPresent(){
        when(userRepository.findByEmail("tester1@gmail.com")).thenReturn(Optional.empty());
        Throwable exception= assertThrows(UserNotfoundException.class,()->userService.findByEmail("tester1@gmail.com"));
        assertEquals("User not found with the given email",exception.getMessage());
    }
    @Test
    void sendVerificationCode(){
        when(userRepository.findByEmail("tester@gmail.com")).thenReturn(Optional.of(user));
        assertNotEquals( "23456",userService.sendVerificationCode("tester@gmail.com"));
    }

    @Test
    void sendVerificationCodeWithException(){
        when(userRepository.findByEmail("tester1@gmail.com")).thenReturn(Optional.empty());
        Throwable exception= assertThrows(UserNotfoundException.class,()->userService.sendVerificationCode("tester1@gmail.com"));
        assertEquals("User not found with the given email",exception.getMessage());
    }
    @Test
    void updateUser(){
        int id=1;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.convertToUserEntity(newUser)).thenReturn(user);
        assertEquals(newUser,userService.updateUser(id,newUser));
    }

    @Test
    void updateUserWhenUserNotPresent(){
        int id=2;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        when(userMapper.convertToUserEntity(newUser)).thenReturn(user);
        Throwable exception= assertThrows(UserNotfoundException.class,()->userService.updateUser(id,newUser));
        assertEquals("User not found with the given id",exception.getMessage());
    }
}
