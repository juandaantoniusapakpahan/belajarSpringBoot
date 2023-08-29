package com.example.payroll.controller;

import com.example.payroll.model.entity.SalaryMatrix;
import com.example.payroll.model.request.SalaryMatrixRequest;
import com.example.payroll.service.SalaryMatrixService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/salaryMatrix")
public class SalaryMatrixController {

    @Autowired
    private SalaryMatrixService salaryMatrixService;


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody SalaryMatrixRequest salaryMatrix) {
        SalaryMatrix _result = salaryMatrixService.create(salaryMatrix);
        return new ResponseEntity<>(_result, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        salaryMatrixService.delete(id);
        return new ResponseEntity<>("successfully delete salary matrix", HttpStatus.OK);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody SalaryMatrixRequest salaryMatrixRequest){
        SalaryMatrix _result = salaryMatrixService.update(id, salaryMatrixRequest);
        return new ResponseEntity<>(_result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam int page, @RequestParam int size, @RequestParam String sortColumn){
        List<SalaryMatrix> _result =  salaryMatrixService.findAll(page, size, sortColumn);
        return new ResponseEntity<>(_result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        SalaryMatrix salaryMatrix = salaryMatrixService.findById(id);
        return new ResponseEntity<>(salaryMatrix, HttpStatus.OK);
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<?> findByGrade(@PathVariable int grade){
       SalaryMatrix salaryMatrix = salaryMatrixService.findByGrade(grade);
       return new ResponseEntity<>(salaryMatrix, HttpStatus.OK);
    }
}
