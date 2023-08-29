package com.example.payroll.model.response;

import com.example.payroll.model.entity.Payroll;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PayrollResponse {
    private Long payRollId;
    private Integer attend;
    private Integer absent;
    private Double basicSalary;
    private Double payCut;
    private String period;
    private Double headOfFamily;
    private Double allowance;
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
        this.employeeId = payroll.getEmployee().getEmployeeId();
        this.total = payroll.getTotal();
    }
}
