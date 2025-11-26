package com.example.UserService.Service;

import com.example.UserService.Exceptions.InvalidTokenException;
import com.example.UserService.Exceptions.PasswordMismacthException;
import com.example.UserService.Model.Token;
import com.example.UserService.Model.User;
import com.example.UserService.Repository.TokenRepository;
import com.example.UserService.Repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
/*
* Once we add spring security dependency in pom.xml , all the requests (signup , login etc..) will be authenticated (protected) & will give 401 error
* we should to override this by bypassing the security
*/
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signUp(String name, String email, String password) {
        // check if there is a user with same details or not
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            // redirect to login page (it will be the task of frontend) so return the same user
            return userOptional.get();
        }
        // now create a new user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        // we shouldn't show the password directly in db, so use bcrypt password encoder
        // here everytime signup is called we are creating new object, it's not advisable

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(password));

        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

   /*
    If email & password matches, then generate a token & return it to the user
    If not then show that email password is wrong / navigate to signup page
    */

    @Override
    public Token login(String email, String password) throws PasswordMismacthException {
        Optional<User> findByEmail = userRepository.findByEmail(email);
        if(findByEmail.isEmpty()){
            return null;
            // navigate it to signup page
        }
        User user = findByEmail.get();
        // Now we cannot compare the password stored in db with request param since password in db is encoded with bcryptpassword
        if(! bCryptPasswordEncoder.matches(password, user.getPassword()) ){
            // login unsuccesfull
            throw new PasswordMismacthException("Incorrect Password");
        }
        // login successfull & generate the token
        // Add apache commons lang dependency into pom.xml
        Token token = new Token();
        token.setUser(user);
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));

        // set expiry date 30 days ahead from current time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,30);
        Date expiryDate = calendar.getTime();

        token.setExpiryAt(expiryDate);

        return tokenRepository.save(token);
    }

    @Override
    public User validateToken(String tokenValue) throws InvalidTokenException {
        Optional<Token> optionalToken = tokenRepository.findByTokenValueAndExpiryAtGreaterThan(tokenValue,new Date());
        if(optionalToken.isEmpty()){
            throw new InvalidTokenException("Invalid token");
        }
        // token is valid
        Token token = optionalToken.get();
        return token.getUser();
    }
}
