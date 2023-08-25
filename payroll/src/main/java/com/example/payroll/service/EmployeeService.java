package com.example.payroll.service;

import com.example.payroll.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeService {
    public Employee save(Employee employee);
    public List<Employee> findAll(Pageable pageable);
    public Employee findById(Long id);
    public List<Employee> findByGrade(int grade, Pageable pageable);
    public Employee deleteById(Long id);
    public List<Employee> findNonActive();
}
