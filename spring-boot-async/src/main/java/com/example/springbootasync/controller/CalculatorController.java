package com.example.springbootasync.controller;


import com.example.springbootasync.model.Calculator;
import com.example.springbootasync.model.CalculatorRequest;
import com.example.springbootasync.model.ResponseTemplate;
import com.example.springbootasync.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/calculator")
@Slf4j
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;


    @PostMapping
    public ResponseEntity<?> postCalculator(@RequestBody CalculatorRequest calculatorRequest) throws Exception {
        Calculator calculator = calculatorService.getCalculator(calculatorRequest.getNumbers());
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(), calculator);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }
}
