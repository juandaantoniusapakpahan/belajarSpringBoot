package com.example.payroll.repository;

import com.example.payroll.model.entity.SalaryMatrix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SalaryMatrixRepository extends JpaRepository<SalaryMatrix, Long> {
    Page<SalaryMatrix> findAll(Pageable pageable);
    Optional<SalaryMatrix> findByGrade(int grade);
}
