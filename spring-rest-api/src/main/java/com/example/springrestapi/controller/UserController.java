package com.example.springrestapi.controller;


import com.example.springrestapi.model.User;
import com.example.springrestapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired private UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user){
        User _result = userService.save(user);
        return new ResponseEntity<>(_result, HttpStatus.CREATED);
    }
}
