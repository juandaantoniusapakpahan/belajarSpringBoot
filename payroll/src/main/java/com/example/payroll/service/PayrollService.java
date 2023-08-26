package com.example.payroll.service;

import com.example.payroll.model.entity.Payroll;
import com.example.payroll.model.request.PayrollRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollService {
    public Payroll save(PayrollRequest payrollRequest);
}
