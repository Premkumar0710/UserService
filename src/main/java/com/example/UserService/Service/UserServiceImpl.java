package com.example.UserService.Service;

import com.example.UserService.Model.Token;
import com.example.UserService.Model.User;
import com.example.UserService.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
/*
* Once we add spring security dependency in pom.xml , all the requests (signup , login etc..) will be authenticated (protected) & will give 401 error
* we should to override this by bypassing the security
*/
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @Override
    public Token login(String email, String password) {
        return null;
    }

    @Override
    public User validateToken(String tokenValue) {
        return null;
    }
}
