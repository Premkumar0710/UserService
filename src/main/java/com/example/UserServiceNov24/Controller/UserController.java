package com.example.UserServiceNov24.Controller;

import com.example.UserServiceNov24.Dtos.*;
import com.example.UserServiceNov24.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
// in the below methods we can also pass params directly like string name , string email... but it's not following standards
    public LoginResponseDto login(LoginRequestDto requestDto){
        return null;
    }

    public UserDto signup(SignupRequestDto requestDto){
        return null;
    }

    public UserDto validateToken(ValidateTokenRequestDto requestDto){
        return null;
    }

    public LogoutResponseDto logout(LogoutRequestDto requestDto){
        return null;
    }
}
