package com.example.oauthtwologin.controller;



import com.example.oauthtwologin.model.User;
import com.example.oauthtwologin.service.UserService;
import ch.qos.logback.core.joran.sanity.Pair;
import com.nimbusds.openid.connect.sdk.federation.api.FederationAPIError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class UserController {

    @Autowired private UserService userService;

   @PostMapping(path = "/users")
    public @ResponseBody User addNewUser(@Valid @RequestBody User user1){

     User _result = userService.create(user1);
     if (_result == null){
         throw new RuntimeException("email or username already exists");
     }

     return _result;
   }

}
