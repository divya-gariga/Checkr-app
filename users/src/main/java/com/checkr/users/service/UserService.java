package com.checkr.users.service;

import com.checkr.users.dto.UserCreationDTO;
import com.checkr.users.dto.UserDTO;

public interface UserService {

    String registerUser(UserCreationDTO userCreationDTO);
    UserDTO findByEmail(String email);
    String sendVerificationCode(String email);
    UserCreationDTO updateUser(int userId,UserCreationDTO userCreationDTO);

}
