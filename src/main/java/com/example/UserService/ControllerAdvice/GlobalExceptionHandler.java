package com.example.UserService.ControllerAdvice;

import com.example.UserService.Dtos.ExceptionDto;
import com.example.UserService.Exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionDto> invalidTokenException(){

        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Unauthorized access, Please try again with correct credentials");
        exceptionDto.setStatusCode(401);

        return new ResponseEntity<>(exceptionDto,HttpStatus.UNAUTHORIZED);
    }
}
