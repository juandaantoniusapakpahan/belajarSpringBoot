package com.example.payroll.model.entity;


import com.example.payroll.model.request.SalaryMatrixRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "salaryMatrixs")
public class SalaryMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryMatrixId;

    @Column(nullable = false, unique = true)
    @Min(1)
    private int grade;

    @NotNull(message = "basicSalary is mandatory")
    @Column(nullable = false)
    @DecimalMin("100000.0")
    private double basicSalary;

    @NotNull(message = "payCut is mandatory")
    @Column(nullable = false)
    @DecimalMin("10000.0")
    private double payCut;

    @NotNull(message = "allowance is mandatory")
    @Column(nullable = false)
    @DecimalMin("10000.0")
    private double allowance;

    @NotNull(message = "headOfFamily is mandatory")
    @Column(nullable = false)
    @DecimalMin("100000.0")
    private double headOfFamily;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp created_at;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp updated_at;


    public SalaryMatrix(SalaryMatrixRequest sMR){
        this.grade = sMR.getGrade();
        this.basicSalary = sMR.getBasicSalary();
        this.allowance = sMR.getAllowance();
        this.headOfFamily = sMR.getHeadOfFamily();
        this.payCut = sMR.getPayCut();
    }
}
