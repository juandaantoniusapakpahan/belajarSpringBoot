package com.example.payroll.controller;


import com.example.payroll.model.Employee;
import com.example.payroll.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Employee employee){
        Employee employee1 = employeeService.save(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }
}
