package com.example.payroll.controller;


import com.example.payroll.model.entity.Payroll;
import com.example.payroll.model.request.PayrollRequest;
import com.example.payroll.model.response.PayrollResponse;
import com.example.payroll.service.PayrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {

    @Autowired private PayrollService payrollService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PayrollRequest request){
        Payroll payroll = payrollService.save(request);
        PayrollResponse payrollResponse = new PayrollResponse(payroll);
        return new ResponseEntity<>(payrollResponse, HttpStatus.CREATED);
    }
}
