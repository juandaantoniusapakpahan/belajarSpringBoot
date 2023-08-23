package com.example.springdatamysql.controller;


import com.example.springdatamysql.Exception.AuthorizeError;
import com.example.springdatamysql.Exception.NotFoundError;
import com.example.springdatamysql.model.Foos;
import com.example.springdatamysql.service.FoosService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FoosController {

    @Autowired
    private FoosService foosService;

    @RequestMapping(value = "/foos", method = RequestMethod.POST, headers = "Z-Auth=APP")
    @ResponseBody
    public Foos create(@RequestBody Foos foos, HttpServletRequest request){
//        String requestKey = request.getHeader("X-Auth");
//        if (requestKey!= "APP"){
//            throw new AuthorizeError();
//        }
        return foosService.create(foos);
    }


    @RequestMapping(value = "/foos", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Foos> findAll(){
        return foosService.findAll();
    }

    @RequestMapping(value = "/foos/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Foos> findById(@PathVariable Long id){
        Optional<Foos>  foos = foosService.findById(id);
        if (foos == null){
            throw new NotFoundError();
        }
        return foos;
    }
}
