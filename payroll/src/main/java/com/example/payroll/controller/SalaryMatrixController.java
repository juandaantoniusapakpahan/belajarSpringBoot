package com.example.payroll.controller;

import com.example.payroll.helper.ErrorResponseTemplate;
import com.example.payroll.model.SalaryMatrix;
import com.example.payroll.service.SalaryMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/salaryMatrix")
public class SalaryMatrixController {

    @Autowired
    private SalaryMatrixService salaryMatrixService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody SalaryMatrix salaryMatrix) {
        SalaryMatrix _result = salaryMatrixService.create(salaryMatrix);
        return new ResponseEntity<>(_result, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        salaryMatrixService.delete(id);
        return new ResponseEntity<>("successfully delete salary matrix", HttpStatus.OK);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SalaryMatrix salaryMatrix){
        SalaryMatrix _result = salaryMatrixService.update(id, salaryMatrix);
        return new ResponseEntity<>(_result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<SalaryMatrix> _result =  salaryMatrixService.findAll();
        return new ResponseEntity<>(_result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        SalaryMatrix salaryMatrix = salaryMatrixService.findById(id);
        return new ResponseEntity<>(salaryMatrix, HttpStatus.OK);
    }
}
