package com.example.payroll.repository;

import com.example.payroll.model.SalaryMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SalaryMatrixRepository extends JpaRepository<SalaryMatrix, Long> {
    SalaryMatrix findByGrade(Integer grade);
    List<SalaryMatrix> findAll();
}
