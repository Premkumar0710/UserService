package com.example.UserService.Exceptions;

// if you extend exception; then it's a checked exception
public class PasswordMismacthException extends Exception{
    public PasswordMismacthException(String message){
        super(message);
    }
}
