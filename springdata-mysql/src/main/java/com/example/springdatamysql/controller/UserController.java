package com.example.springdatamysql.controller;


import com.example.springdatamysql.Exception.NotFoundError;
import com.example.springdatamysql.model.User;
import com.example.springdatamysql.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/demo")
public class UserController {
    @Autowired
    private UserService userService ;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email){
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        return userService.create(n);
    }

    @GetMapping()
    public @ResponseBody Iterable<User> findAll(){
        return userService.findAll();
    }

    @GetMapping(path = "/find-query")
    public @ResponseBody Iterable<User> findUserUsingQuery(@RequestParam String name, @RequestParam String email){
        return userService.findUserUsingQuery(name, email);
    }

    @GetMapping(path = "/find-query-native")
    public  @ResponseBody Iterable<User> findUserUsingNativeQuery(@RequestParam String name, @RequestParam String email){
        return userService.findUserUsingNativeQuery(name, email);
    }

    @GetMapping(path = "/findUserUsingNativeQueryLike")
    public  @ResponseBody Iterable<User> findUserUsingNativeQueryLike(@RequestParam String email){
        return userService.findUserUsingNativeQueryLike(email);
    }


    @GetMapping(path = "/findAllUsersSort")
    public @ResponseBody Iterable<User> findAllUsersSort(){
        return userService.findAllUsersSort();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String delete(@PathVariable Integer id){
        String _result = userService.delete(id);
        if (_result!=""){
            throw new NotFoundError("user not found");
        }

        return "successfully delete account";
    }

    @PutMapping(path = "/{id}")
    public  @ResponseBody User update(@RequestBody User user, @PathVariable Integer id){

        User _user = userService.update(user, id);
        if (_user == null){
            throw new NotFoundError("user not found");
        }
        return _user;
    }
}
