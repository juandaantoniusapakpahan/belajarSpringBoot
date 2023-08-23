package com.example.springdatamysql.service;

import com.example.springdatamysql.model.User;
import com.example.springdatamysql.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(){

    }
    public String create(User user){
         userRepository.save(user);
         return "successfully added account";
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public  Iterable<User> findUserUsingQuery(String name, String email){
        return userRepository.findUserUsingQuery(name, email);
    }

    public Iterable<User> findUserUsingNativeQuery(String name, String email){
        return userRepository.findUserUsingNativeQuery(name, email);
    }

    public Iterable<User> findUserUsingNativeQueryLike(String email){
        return userRepository.findUserUsingNativeQueryLike(email);
    }

    public Iterable<User> findAllUsersSort(){
        return userRepository.findAllUsers(Sort.by("name", "email"));
    }

    public String delete(Integer id){
       if( userRepository.findById(id)==null){
           return null;
       }
       userRepository.deleteById(id);
       return "successfully delete user";
    }

    public User update(User user, Integer id){
        Optional<User> user1 = userRepository.findById(id);
        if (user1 == null){
            return null;
        }
        user.setId(id);
        userRepository.save(user);

        return user;
    }
}
