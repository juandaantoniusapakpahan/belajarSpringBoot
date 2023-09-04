package com.example.springbootjwtmysql.controller;


import com.example.springbootjwtmysql.entity.MoveZeroes;
import com.example.springbootjwtmysql.entity.MoveZeroesRequest;
import com.example.springbootjwtmysql.service.MoveZeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moveZeroes")
public class MoveZeroesController {

    @Autowired
    private MoveZeroesService moveZeroesService;

    @PostMapping("/show")
    public MoveZeroes moveZeroes(@RequestBody MoveZeroesRequest moveZeroesRequest){
        MoveZeroes moveZeroes = moveZeroesService.moveZeroes(moveZeroesRequest);
        return moveZeroes;
    }
}
