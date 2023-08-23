package com.example.springdatamysql.service;


import com.example.springdatamysql.model.Foos;
import com.example.springdatamysql.repo.FoosRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoosService {
    @Autowired
    private FoosRespository foosRespository;


    public  FoosService(){

    }
    public Foos create(Foos foos){
        return foosRespository.saveAndFlush(foos);
    }

    public Iterable<Foos> findAll(){
        return foosRespository.findAll();
    }

    public Optional<Foos> findById(Long id){
        return foosRespository.findById(id);
    }

    public Foos update(Long id, Foos foos){
        if (foosRespository.findById(id)==null){
            return null;
        }
        foos.setId(id);
        return foosRespository.saveAndFlush(foos);
    }
}
