package com.example.springbootjwtmysql.repository;

import com.example.springbootjwtmysql.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
