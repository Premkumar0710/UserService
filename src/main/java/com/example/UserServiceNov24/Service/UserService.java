package com.example.UserServiceNov24.Service;

import com.example.UserServiceNov24.Model.Token;
import com.example.UserServiceNov24.Model.User;

public interface UserService {
  User signUp(String name, String email, String password) ;// should not use dto here

    Token login(String email,String password);

    User validateToken(String token);

    void logout(String token);


}
