package com.example.payroll.model.response;

import com.example.payroll.model.entity.Payroll;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PayrollResponse {

    private Long payRollId;
    private int attend;
    private int absent;
    private double basicSalary;
    private double payCut;
    private String period;
    private double headOfFamily;
    private double allowance;
    private Long employeeId;
    private Double total;


    public PayrollResponse(Payroll payroll){
        this.payRollId = payroll.getPayRollId();
        this.attend = payroll.getAttend();
        this.absent = payroll.getAbsent();
        this.basicSalary = payroll.getBasicSalary();
        this.payCut = payroll.getPayCut();
        this.period = payroll.getPeriod();
        this.headOfFamily = payroll.getHeadOfFamily();
        this.allowance = payroll.getAllowance();
        this.employeeId = payroll.getEmployeeId();
        this.total = payroll.getTotal();
    }
}
