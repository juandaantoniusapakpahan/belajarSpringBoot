package com.example.payroll.repository;

import com.example.payroll.model.SalaryMatrix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryMatrixRepository extends JpaRepository<SalaryMatrix, Long> {
    Page<SalaryMatrix> findAll(Pageable pageable);
    Optional<SalaryMatrix> findByGrade(int grade);

}
