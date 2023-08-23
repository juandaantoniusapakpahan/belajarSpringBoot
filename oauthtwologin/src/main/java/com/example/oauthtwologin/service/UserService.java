package com.example.oauthtwologin.service;


import com.example.oauthtwologin.helper.HashComparePassword;
import com.example.oauthtwologin.config.JwtTokenUtil;
import com.example.oauthtwologin.model.User;
import com.example.oauthtwologin.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired private UserRepository userRepository;
    @Autowired private HashComparePassword hashComparePassword;
    @Autowired private JwtTokenUtil jwtTokenUtil;



    public UserService(){

    }
    public User create(User user){


        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1!= null){
            return null;
        }

        User user2 = userRepository.findByEmail(user.getEmail());
        if (user2!= null){
            return null;
        }

        HashComparePassword hashComparePassword = new HashComparePassword();
        String hashPassword = hashComparePassword.hashPassword(user.getPassword());
        user.setPassword(hashPassword);

        // Data can change based on need
        if( user.getIs_verified() == null){
            user.setIs_verified(false);
        }
        // Data can change based on need
        if (user.getIs_admin() == null){
            user.setIs_admin(false);
        }
        User _result = this.userRepository.saveAndFlush(user);
        return _result;
    }

    public String login (String username, String password){
        User user1 =  userRepository.findByUsername(username);

        if (user1 == null){
            return "";
        }

        Boolean same = hashComparePassword.comparePassword(user1.getPassword(), password);

        if (!same) {
            return "";
        }



        String _token = jwtTokenUtil.generateToken(user1);
        return _token;
    }
}
