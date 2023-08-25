package com.example.payroll.service;

import com.example.payroll.model.SalaryMatrix;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryMatrixService {
    public SalaryMatrix create(SalaryMatrix salaryMatrix);
    public void delete(Long id);
    public SalaryMatrix update(Long id, SalaryMatrix salaryMatrix);
    public List<SalaryMatrix> findAll(int page, int size, String sortColumn);
    public SalaryMatrix findById(Long id);
    public SalaryMatrix findByGrade(int grade);

}
