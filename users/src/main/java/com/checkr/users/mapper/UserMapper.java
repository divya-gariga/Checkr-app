package com.checkr.users.mapper;

import com.checkr.users.dto.UserCreationDTO;
import com.checkr.users.dto.UserDTO;
import com.checkr.users.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
  private ModelMapper modelMapper;
    public User convertToUserEntity(UserDTO userDTO){
        return  modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToUserEntity(UserCreationDTO userCreationDTO){
        return  modelMapper.map(userCreationDTO, User.class);
    }

    public UserCreationDTO convertToUserCreationDTO(User user){
        return modelMapper.map(user, UserCreationDTO.class);
    }

}
