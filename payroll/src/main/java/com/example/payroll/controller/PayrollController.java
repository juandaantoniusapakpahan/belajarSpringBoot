package com.example.payroll.controller;


import com.example.payroll.model.entity.Payroll;
import com.example.payroll.model.request.PayrollRequest;
import com.example.payroll.service.PayrollService;
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
@RequestMapping("/api/payrolls")
public class PayrollController {

    @Autowired private PayrollService payrollService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PayrollRequest request){
        Payroll payroll = payrollService.save(request);
        return new ResponseEntity<>(payroll, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,@Valid @RequestBody PayrollRequest payrollRequest){
        Payroll payroll = payrollService.updateById(id, payrollRequest);
        return new ResponseEntity<>(payroll, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam int page, @RequestParam int size, @RequestParam String sortColumn){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
        List<Payroll> payrolls = payrollService.findAll(pageable);
        return new ResponseEntity<>(payrolls, HttpStatus.OK);
    }


    @GetMapping("/employee/{id}")
    private ResponseEntity<?> findByEmployeeId(@PathVariable long id){
        List<Payroll> payrolls = payrollService.findByEmployeeId(id);
        return new ResponseEntity<>(payrolls, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id){
        Payroll payroll = payrollService.findById(id);
        return new ResponseEntity<>(payroll, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable Long id){
       payrollService.deleteById(id);
        return new ResponseEntity<>("successfully delete payroll", HttpStatus.OK);
    }

}
