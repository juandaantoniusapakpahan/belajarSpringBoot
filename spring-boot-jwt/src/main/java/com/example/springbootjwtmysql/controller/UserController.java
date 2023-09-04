package com.example.springbootjwtmysql.controller;


import com.example.springbootjwtmysql.config.IAuthenticationFacade;
import com.example.springbootjwtmysql.entity.*;
import com.example.springbootjwtmysql.service.JwtService;
import com.example.springbootjwtmysql.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        return new ResponseEntity<>("Welcome this endpoint is not secure", HttpStatus.OK);
    }

    @PostMapping("/addNewUser")
    public UserInfo addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserInfoDetails userProfile() {
        Authentication authentication = iAuthenticationFacade.getAuthentication();
        UserInfoDetails userInfo = (UserInfoDetails) authentication.getPrincipal();
        return userInfo;
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public TokenTemplate authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            TokenTemplate tokenTemplate = new TokenTemplate();
            tokenTemplate.setAccessToken(jwtService.generateToken(authRequest.getUsername()));
            return tokenTemplate;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }


}
