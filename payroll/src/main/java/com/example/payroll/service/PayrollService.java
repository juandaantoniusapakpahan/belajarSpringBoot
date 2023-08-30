package com.example.payroll.service;

import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.entity.Payroll;
import com.example.payroll.model.request.PayrollRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollService {
    public Payroll save(PayrollRequest payrollRequest);
    public Payroll updateById(Long id, PayrollRequest request);
    public Page<Payroll> findAll(Pageable pageable);
    public List<Payroll> findByEmployeeId(Long id);
    public void deleteById(Long id);
    public Payroll findById(Long id);
}
