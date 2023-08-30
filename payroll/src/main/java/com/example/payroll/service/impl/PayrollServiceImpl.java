package com.example.payroll.service.impl;

import com.example.payroll.exception.NoSuchPayrollException;
import com.example.payroll.exception.PeriodPayrollExistsException;
import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.entity.Payroll;
import com.example.payroll.model.request.PayrollRequest;
import com.example.payroll.model.entity.SalaryMatrix;
import com.example.payroll.repository.PayrollRepository;
import com.example.payroll.service.EmployeeService;
import com.example.payroll.service.PayrollService;
import com.example.payroll.service.SalaryMatrixService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    @Autowired private EmployeeService employeeService;
    @Autowired private SalaryMatrixService salaryMatrixService;
    @Autowired private PayrollRepository payrollRepository;

    @Override
    public Payroll save(PayrollRequest payrollRequest) {
        try {
            Employee employee = employeeService.findById(payrollRequest.getEmployeeId());
            SalaryMatrix salaryMatrix = salaryMatrixService.findByGrade(employee.getGrade());
            Payroll payroll = new Payroll(payrollRequest);
            payroll.setEmployee(employee);

            payroll.setPayCut(payrollRequest.getAbsent() * salaryMatrix.getPayCut());
            payroll.setAllowance(payrollRequest.getAttend() * salaryMatrix.getAllowance());
            payroll.setBasicSalary(salaryMatrix.getBasicSalary());
            if (employee.getGender().equals("Laki-laki") && employee.getMarried() == true) {
                payroll.setHeadOfFamily(salaryMatrix.getHeadOfFamily());
            }
            payroll.setTotal(payroll.getAllowance() + payroll.getBasicSalary() + payroll.getHeadOfFamily() - payroll.getPayCut());

            return payrollRepository.save(payroll);
        }catch (DataIntegrityViolationException e){
            throw new PeriodPayrollExistsException("payroll with period: "+payrollRequest.getPeriod() + " and this employee " + "already exists" );
        }
    }

    @Override public Payroll updateById(Long id, PayrollRequest payrollRequest){
        try{
            payrollRepository.findById(id).get();
            Employee employee = employeeService.findById(payrollRequest.getEmployeeId());
            SalaryMatrix salaryMatrix = salaryMatrixService.findByGrade(employee.getGrade());
            Payroll payroll = new Payroll(payrollRequest);
            payroll.setPayRollId(id);
            payroll.setEmployee(employee);
            payroll.setPayCut(payrollRequest.getAbsent() * salaryMatrix.getPayCut());
            payroll.setAllowance(payrollRequest.getAttend() * salaryMatrix.getAllowance());
            payroll.setBasicSalary(salaryMatrix.getBasicSalary());

            if (employee.getGender().equals("Laki-laki") && employee.getMarried() == true){
                payroll.setHeadOfFamily(salaryMatrix.getHeadOfFamily());
            }
            payroll.setTotal(payroll.getAllowance() + payroll.getBasicSalary() + payroll.getHeadOfFamily() - payroll.getPayCut());
            return payrollRepository.save(payroll);
        }catch (NoSuchElementException e){
            throw new NoSuchPayrollException("no such payroll");
        }catch (DataIntegrityViolationException e){
            throw new PeriodPayrollExistsException("payroll with period: "+payrollRequest.getPeriod() + " and this employee " + "already exists" );
        }
    }

    @Override
    public Page<Payroll> findAll(Pageable pageable){
        return payrollRepository.findAll(pageable);
    }

    @Override
    public List<Payroll> findByEmployeeId(Long id){
        return payrollRepository.findByEmployeeEmployeeId(id);
    }

    @Override
    public void deleteById(Long id){
        try{
            payrollRepository.findById(id).get();
            payrollRepository.deleteById(id);
        }catch (NoSuchElementException e){
            throw new NoSuchPayrollException("no such payroll");
        }
    }

    @Override
    public Payroll findById(Long id){
        try {
            return payrollRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw new NoSuchPayrollException("no such payroll");
        }
    }
}
