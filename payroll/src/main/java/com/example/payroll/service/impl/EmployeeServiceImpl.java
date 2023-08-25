package com.example.payroll.service.impl;

import com.example.payroll.exception.NikEmployeeExistsException;
import com.example.payroll.model.Employee;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        try{
            employee.setActive(true);
            return  employeeRepository.save(employee);
        }catch (DataIntegrityViolationException e){
            throw new NikEmployeeExistsException("nik employee already exists");
        }
    }
}
