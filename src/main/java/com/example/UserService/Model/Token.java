package com.example.UserService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    private String tokenValue;
    @ManyToOne
    private User user;
    private Date expiryAt;
}

/*
*  1 token -> 1 user
*  m token <- 1 user
*
* So we will store user_id in token table
*
*
* */
