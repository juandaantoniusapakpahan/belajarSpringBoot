package com.example.springbootjwtmysql.service;


import com.example.springbootjwtmysql.entity.Role;
import com.example.springbootjwtmysql.entity.UserInfo;
import com.example.springbootjwtmysql.entity.UserInfoDetails;
import com.example.springbootjwtmysql.repository.RoleRepository;
import com.example.springbootjwtmysql.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {


    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public UserInfo addUser(UserInfo userInfo) {
        List<Role> roles = new ArrayList<>();
        for (Role role : userInfo.getRole()) {
            roleRepository.findById(role.getId()).ifPresent(roles::add);
        }
        userInfo.setRole(roles);

        userInfo.setPassword(encoder.encode(userInfo.getPassword()));

        return repository.save(userInfo);
    }

}
