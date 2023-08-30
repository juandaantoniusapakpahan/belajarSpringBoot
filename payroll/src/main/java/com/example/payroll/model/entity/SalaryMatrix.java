package com.example.payroll.model.entity;


import com.example.payroll.model.request.SalaryMatrixRequest;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "salaryMatrixs")
public class SalaryMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_matrix_id")
    private Long salaryMatrixId;

    @Column(nullable = false, unique = true)
    private int grade;

    @Column(nullable = false)
    private double basicSalary;

    @Column(nullable = false)
    private double payCut;

    @Column(nullable = false)
    private double allowance;

    @Column(nullable = false)
    private double headOfFamily;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp created_at;

    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp updated_at;

    public SalaryMatrix(SalaryMatrixRequest sMR){
        this.salaryMatrixId =sMR.getSalaryMatrixId();
        this.grade = sMR.getGrade();
        this.basicSalary = sMR.getBasicSalary();
        this.allowance = sMR.getAllowance();
        this.headOfFamily = sMR.getHeadOfFamily();
        this.payCut = sMR.getPayCut();
    }

    public SalaryMatrix(Long salaryMatrixId, int grade, double basicSalary, double payCut, double allowance, double headOfFamily) {
        this.salaryMatrixId = salaryMatrixId;
        this.grade = grade;
        this.basicSalary = basicSalary;
        this.payCut = payCut;
        this.allowance = allowance;
        this.headOfFamily = headOfFamily;
    }

}
