package com.example.payroll.controller;

import com.example.payroll.model.entity.SalaryMatrix;
import com.example.payroll.model.request.SalaryMatrixRequest;
import com.example.payroll.model.response.OutPutResponseTemp;
import com.example.payroll.service.SalaryMatrixService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/salaryMatrix")
public class SalaryMatrixController {

    @Autowired
    private SalaryMatrixService salaryMatrixService;


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody SalaryMatrixRequest salaryMatrix) {
        SalaryMatrix _result = salaryMatrixService.create(salaryMatrix);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<SalaryMatrix>(HttpStatus.CREATED.name(),_result);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        salaryMatrixService.delete(id);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<>(HttpStatus.OK.name(), "successfully delete salary matrix with ID: "+id);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody SalaryMatrixRequest salaryMatrixRequest){
        SalaryMatrix _result = salaryMatrixService.update(id, salaryMatrixRequest);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<>(HttpStatus.OK.name(), _result);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam int page, @RequestParam int size, @RequestParam String sortColumn){
        Page<SalaryMatrix> _result =  salaryMatrixService.findAll(page, size, sortColumn);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<>(HttpStatus.OK.name(),_result);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        SalaryMatrix salaryMatrix = salaryMatrixService.findById(id);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<>(HttpStatus.OK.name(), salaryMatrix);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<?> findByGrade(@PathVariable int grade){
       SalaryMatrix salaryMatrix = salaryMatrixService.findByGrade(grade);
       OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<>(HttpStatus.OK.name(), salaryMatrix);
       return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }
}
