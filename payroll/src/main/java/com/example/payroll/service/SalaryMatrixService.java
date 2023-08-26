package com.example.payroll.service;

import com.example.payroll.model.entity.SalaryMatrix;
import com.example.payroll.model.request.SalaryMatrixRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryMatrixService {
    public SalaryMatrix create(SalaryMatrixRequest salaryMatrix);
    public void delete(Long id);
    public SalaryMatrix update(Long id, SalaryMatrixRequest salaryMatrixRequest);
    public List<SalaryMatrix> findAll(int page, int size, String sortColumn);
    public SalaryMatrix findById(Long id);
    public SalaryMatrix findByGrade(int grade);

}
