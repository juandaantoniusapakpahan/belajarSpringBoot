package com.example.payroll.controller;


import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.request.EmployeeRequest;
import com.example.payroll.model.response.EmployeeResponse;
import com.example.payroll.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody EmployeeRequest employee){
        Employee employee1 = employeeService.save(employee);
        EmployeeResponse employeeResponse = new EmployeeResponse(employee1);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest){
        Employee employee = employeeService.updateById(id, employeeRequest);
        EmployeeResponse employeeResponse = new EmployeeResponse(employee);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam int page, @RequestParam int size, @RequestParam String sortColumn){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
        List<Employee>employees = employeeService.findAll(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @GetMapping("/grade/{grade}")
    public ResponseEntity<?> findByGrade(@PathVariable int grade, @RequestParam int page, @RequestParam int size, @RequestParam String sortColumn){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
        List<Employee> employees = employeeService.findByGrade(grade, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Employee employee = employeeService.deleteById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/nonActive")
    public ResponseEntity<?> findNonActive(){
        List<Employee> employees = employeeService.findNonActive();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
