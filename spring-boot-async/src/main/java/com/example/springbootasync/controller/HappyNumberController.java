package com.example.springbootasync.controller;


import com.example.springbootasync.model.ResponseTemplate;
import com.example.springbootasync.service.HappyNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/happyNumber")
public class HappyNumberController {
    @Autowired
    HappyNumberService happyNumberService;


    @GetMapping()
    public ResponseEntity<?> getHappy(@RequestParam int number){
        Boolean result = happyNumberService.getHappy(number);
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(),result);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }
}