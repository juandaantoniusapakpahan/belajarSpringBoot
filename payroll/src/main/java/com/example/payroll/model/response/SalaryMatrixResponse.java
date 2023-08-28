package com.example.payroll.model.response;


import com.example.payroll.model.entity.SalaryMatrix;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class SalaryMatrixResponse {

    private Long salaryMatrixId;
    private int grade;
    private double basicSalary;
    private double payCut;
    private double allowance;
    private double headOfFamily;
    @JsonIgnore
    private Timestamp created_at;
    @JsonIgnore
    private Timestamp updated_at;
    public SalaryMatrixResponse(SalaryMatrix sm){
        this.salaryMatrixId = sm.getSalaryMatrixId();
        this.grade = sm.getGrade();
        this.basicSalary = sm.getBasicSalary();
        this.payCut = sm.getPayCut();
        this.allowance = sm.getAllowance();
        this.headOfFamily = sm.getHeadOfFamily();
        this.created_at = sm.getCreated_at();
        this.updated_at = sm.getUpdated_at();
    }
}
