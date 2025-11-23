package com.example.UserService.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Here for signup we require only these 3 details , so instead of using entire user object we can use this..
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
}
