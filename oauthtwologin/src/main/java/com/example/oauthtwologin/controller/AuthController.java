package com.example.oauthtwologin.controller;


import com.example.oauthtwologin.Exception.AuthenticationError;
import com.example.oauthtwologin.request.JwtRequest;
import com.example.oauthtwologin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private  UserService userService;

    @PostMapping(path = "/auth")
    public @ResponseBody String login(@RequestBody JwtRequest user){
       String _result = userService.login(user.getUsername(), user.getPassword());

       if (_result == ""){
           throw new AuthenticationError("invalid username or password");
       }
       return _result;
    }
}
