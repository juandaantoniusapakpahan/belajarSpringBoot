package com.example.springdatamysql.repo;

import com.example.springdatamysql.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.name = ?1 AND u.email = ?2")
    List<User> findUserUsingQuery(String name, String email);


    @Query(value = "SELECT * FROM users u WHERE u.name = ?1 AND u.email = ?2",nativeQuery = true)
    List<User> findUserUsingNativeQuery(String name, String email);


    @Query(value = "Select * FROM users u where u.email like %?1%", nativeQuery = true)
    List<User> findUserUsingNativeQueryLike(String email);

    @Query("SELECT u FROM User u")
    List<User> findAllUsers(Sort sort);
}
