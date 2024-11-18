package com.example.UserServiceNov24.Repository;

import com.example.UserServiceNov24.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long>{
    @Override
    Token save(Token token);


    Optional<Token> findByValueAndDeleted(String token, Boolean deleted);

    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String token, boolean deleted, Date expiryAt);
}
