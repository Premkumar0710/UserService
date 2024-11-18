package com.example.UserServiceNov24.Dtos;

import com.example.UserServiceNov24.Model.Role;
import com.example.UserServiceNov24.Model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
// We can call this as SignUpResponseDto as well. We have used userDto to make it generic
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;

    public static UserDto fromUser(User user){
        if(user == null) {return null;}

        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
