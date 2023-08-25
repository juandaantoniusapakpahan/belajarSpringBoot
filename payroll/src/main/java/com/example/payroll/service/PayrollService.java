package com.example.payroll.service;

import com.example.payroll.model.Payroll;
import com.example.payroll.model.PayrollRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollService {
    public Payroll save(PayrollRequest payrollRequest);
}
