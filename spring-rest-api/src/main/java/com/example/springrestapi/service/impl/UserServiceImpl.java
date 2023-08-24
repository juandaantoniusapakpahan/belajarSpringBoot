package com.example.springrestapi.service.impl;

import com.example.springrestapi.exception.UserAlreadyExistsException;
import com.example.springrestapi.model.User;
import com.example.springrestapi.repository.UserRepository;
import com.example.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserRepository userRepository;
    @Override
    public User save(User user) {
        try {
            return userRepository.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new UserAlreadyExistsException("user already exists");
        }
    }
}
