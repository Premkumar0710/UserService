package com.example.UserServiceNov24.Controller;

import com.example.UserServiceNov24.Dtos.*;
import com.example.UserServiceNov24.Dtos.ResponseStatus;
import com.example.UserServiceNov24.Model.Token;
import com.example.UserServiceNov24.Model.User;
import com.example.UserServiceNov24.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
// in the below methods we can also pass params directly like string name , string email... but it's not following standards
@PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto){
       Token token =  userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        LoginResponseDto responseDto = new LoginResponseDto();
    responseDto.setToken(token);
    return responseDto;
    }

    @PostMapping("/signup")
    public SignUpResponseDto signup(@RequestBody SignupRequestDto requestDto){
        User user = userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        SignUpResponseDto responseDto = new SignUpResponseDto();
        responseDto.setUser(user);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return responseDto;
    }

    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") String token){
        User user = userService.validateToken(token);
        return UserDto.fromUser(user);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto){
        ResponseEntity<Void> responseEntity = null;

        try{
            userService.logout(requestDto.getToken());
            responseEntity = new ResponseEntity<>(
                    HttpStatus.OK
            );
        } catch (Exception e){
            responseEntity = new ResponseEntity<>(
                    HttpStatus.UNAUTHORIZED
            );
        }

        return responseEntity;
    }
}
