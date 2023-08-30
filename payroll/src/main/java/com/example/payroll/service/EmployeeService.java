package com.example.payroll.service;

import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.request.EmployeeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeService {
    public Employee save(EmployeeRequest employee);
    public Page<Employee> findAll(Pageable pageable);
    public Employee findById(Long id);
    public List<Employee> findByGrade(int grade, Pageable pageable);
    public Employee deleteById(Long id);
    public List<Employee> findNonActive();
    public Employee updateById(Long id, EmployeeRequest employeeRequest);
}
