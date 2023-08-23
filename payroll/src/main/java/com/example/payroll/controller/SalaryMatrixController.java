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
        try{
            SalaryMatrix _result = salaryMatrixService.create(salaryMatrix);
            return new ResponseEntity<>(_result, HttpStatus.CREATED);
        }catch (Exception e){

            ErrorResponseTemplate err = new ErrorResponseTemplate(
                    400,
                   "BAD REQUEST",
                    e.getMessage(),
                    "/api/salaryMatrix");
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            salaryMatrixService.delete(id);
            return new ResponseEntity<>("successfully delete salary matrix", HttpStatus.OK);
        }catch (Exception e){
            ErrorResponseTemplate err = new ErrorResponseTemplate(
                    404,
                    "NOT FOUND",
                    e.getMessage(),
                    "/api/salaryMatrix/"+id
            );
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SalaryMatrix salaryMatrix){
        try{
            SalaryMatrix _result = salaryMatrixService.update(id, salaryMatrix);
            return new ResponseEntity<>(_result, HttpStatus.OK);
        }catch (Exception e){
            ErrorResponseTemplate err= new ErrorResponseTemplate();
            HttpStatus s;
            if (e.getMessage()== "No value present") {
                err.setStatus(404);
                err.setError("NOT FOUND");
                err.setMessage(e.getMessage());
                err.setPath("/api/salaryMatrix/" + id);
                s = HttpStatus.NOT_FOUND;
            }else{
                err.setStatus(400);
                err.setError("BAD REQUEST");
                err.setMessage(e.getMessage());
                err.setPath("/api/salaryMatrix/"+id);
                s = HttpStatus.BAD_REQUEST;
            }
           return new ResponseEntity<>(err,s);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<SalaryMatrix> _result =  salaryMatrixService.findAll();
        return new ResponseEntity<>(_result, HttpStatus.OK);
    }
}
