package com.example.UserService.Controller;

import com.example.UserService.Dtos.LoginRequestDto;
import com.example.UserService.Dtos.SignUpRequestDto;
import com.example.UserService.Dtos.TokenDto;
import com.example.UserService.Dtos.UserDto;
import com.example.UserService.Exceptions.InvalidTokenException;
import com.example.UserService.Exceptions.PasswordMismacthException;
import com.example.UserService.Model.Token;
import com.example.UserService.Model.User;
import com.example.UserService.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    // constructor injection
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    // are u supposed to return entire user object which has password ? Create userDto
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto){
       User user = userService.signUp(
                requestDto.getName(), requestDto.getEmail(), requestDto.getPassword()
        );
       // but we have to return userdto
        return UserDto.fromUser(user);
    }

    @PostMapping("/login")
    // Here after validating i/p in db we need to create new token; so we used post mapping here.
    // we should not return entire token object as it will expose user details which is present inside.
    public TokenDto login(@RequestBody LoginRequestDto requestDto) throws PasswordMismacthException {
       Token token = userService.login(requestDto.getEmail(), requestDto.getPassword());
       return TokenDto.from(token);
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDto validateToken(@PathVariable("tokenValue") String tokenValue) throws InvalidTokenException {
        User user = userService.validateToken(tokenValue);
        return UserDto.fromUser(user);
    }

    // to-do
    // go to db & mark the token as inactive
    public Boolean logOut(){
        return null;
    }
}
