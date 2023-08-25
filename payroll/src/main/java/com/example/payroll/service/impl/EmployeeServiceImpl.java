package com.example.payroll.service.impl;

import com.example.payroll.exception.NikEmployeeExistsException;
import com.example.payroll.exception.NoSuchEmployeeException;
import com.example.payroll.exception.NoSuchSalaryMatrixException;
import com.example.payroll.model.Employee;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.repository.SalaryMatrixRepository;
import com.example.payroll.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private SalaryMatrixRepository salaryMatrixRepository;

    @Override
    public Employee save(Employee employee) {
        try{
            salaryMatrixRepository.findByGrade(employee.getGrade());
            employee.setActive(true);
            return  employeeRepository.save(employee);
        }catch (NoSuchElementException e){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }
        catch (DataIntegrityViolationException e){
            throw new NikEmployeeExistsException("nik employee already exists");
        }
    }

    @Override
    public List<Employee> findAll(Pageable pageable){
        return employeeRepository.findAll(pageable).toList();
    }
    @Override
    public Employee findById(Long id){
        try{
            return employeeRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw new NoSuchEmployeeException("employee not found");
        }
    }
    @Override
    public List<Employee> findByGrade(int grade, Pageable pageable){
      return employeeRepository.findByGrade(grade, pageable);
    }

    @Override
    public Employee deleteById(Long id){
        try {
            employeeRepository.findById(id).get();
            Boolean isActive = false;
            employeeRepository.updateById(isActive,id);
            return employeeRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw new NoSuchEmployeeException("employee not found");
        }
    }

    @Override
    public List<Employee> findNonActive(){
        return employeeRepository.findByActive(false);
    }
}
