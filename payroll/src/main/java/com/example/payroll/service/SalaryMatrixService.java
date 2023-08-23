package com.example.payroll.service;

import com.example.payroll.model.SalaryMatrix;

import java.util.List;

public interface SalaryMatrixService {
    public SalaryMatrix create(SalaryMatrix salaryMatrix);
    public void delete(Long id);
    public SalaryMatrix update(Long id, SalaryMatrix salaryMatrix);
    public List<SalaryMatrix> findAll();
    public SalaryMatrix findById(Long id);
}
