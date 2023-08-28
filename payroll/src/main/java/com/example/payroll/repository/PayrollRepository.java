package com.example.payroll.repository;

import com.example.payroll.model.entity.Payroll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    public Page<Payroll> findAll(Pageable pageable);
    public List<Payroll> findByEmployeeEmployeeId(Long id);
}
