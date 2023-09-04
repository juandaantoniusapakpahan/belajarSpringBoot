package com.example.springbootjwtmysql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserInfoDetails implements UserDetails {

    private Integer id;
    private String name;

    @JsonIgnore
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(UserInfo userInfo) {
        id = userInfo.getId();
        name = userInfo.getName();
        password = userInfo.getPassword();
        authorities = new ArrayList<>(userInfo.getRole().size());
        for (Role role : userInfo.getRole())
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
