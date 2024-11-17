package com.example.UserServiceNov24.Dtos;

import com.example.UserServiceNov24.Model.Role;
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
}
