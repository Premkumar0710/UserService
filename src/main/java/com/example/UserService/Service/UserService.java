package com.example.UserService.Service;

import com.example.UserService.Model.Token;
import com.example.UserService.Model.User;

public interface UserService {
    // all the methods inside an interface are public & static. So, no need of using public keyword
    // Use of Dto's aren't required here, anyway we have it in controller right ? , so it will take care of filtering out only the reqd details

    User signUp(String name, String email, String password);
    Token login(String email, String password);
    User validateToken(String tokenValue);

    // add logout as well
}
