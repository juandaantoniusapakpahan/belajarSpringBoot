package com.example.springdatamysql.repo;

import com.example.springdatamysql.model.Foos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoosRespository extends JpaRepository<Foos, Long> {

}
