package com.example.UserService.Dtos;

import com.example.UserService.Model.Role;
import com.example.UserService.Model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long userId;
    private String email;
    private List<Role> roles;

    public static UserDto fromUser(User user){

        if(user == null){
            return null;
        }
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
