package com.example.UserServiceNov24.Dtos;

import com.example.UserServiceNov24.Model.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private Token token;
}
