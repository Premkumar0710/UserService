package com.example.UserServiceNov24.Dtos;

import com.example.UserServiceNov24.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private User user;
    private ResponseStatus responseStatus;
}
