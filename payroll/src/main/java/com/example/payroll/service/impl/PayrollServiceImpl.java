package com.example.payroll.service.impl;

import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.entity.Payroll;
import com.example.payroll.model.request.PayrollRequest;
import com.example.payroll.model.entity.SalaryMatrix;
import com.example.payroll.repository.PayrollRepository;
import com.example.payroll.service.EmployeeService;
import com.example.payroll.service.PayrollService;
import com.example.payroll.service.SalaryMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired private EmployeeService employeeService;
    @Autowired private SalaryMatrixService salaryMatrixService;
    @Autowired private PayrollRepository payrollRepository;

    @Override
    public Payroll save(PayrollRequest payrollRequest) {
        Employee employee = employeeService.findById(payrollRequest.getEmployeeId());
        SalaryMatrix salaryMatrix = salaryMatrixService.findByGrade(employee.getGrade());
        Payroll payroll = new Payroll(payrollRequest);

        payroll.setPayCut(payrollRequest.getAbsent() * salaryMatrix.getPayCut());
        payroll.setAllowance(payrollRequest.getAttend() * salaryMatrix.getAllowance());
        payroll.setBasicSalary(salaryMatrix.getBasicSalary());
        if (employee.getGender().equals("Laki-laki") && employee.isMarried() == true){
            payroll.setHeadOfFamily(salaryMatrix.getHeadOfFamily());
        }
        payroll.setTotal(payroll.getAllowance() + payroll.getBasicSalary() + payroll.getHeadOfFamily() - payroll.getPayCut());

        return payrollRepository.save(payroll);
    }
}