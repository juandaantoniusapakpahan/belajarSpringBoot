package com.example.payroll.model.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class SalaryMatrixRequest {
    @JsonIgnore
    private Long salaryMatrixId;

    @Min(1)
    private int grade;

    @NotNull(message = "basicSalary is mandatory")
    @DecimalMin("100000.0")
    private double basicSalary;

    @NotNull(message = "payCut is mandatory")
    @DecimalMin("10000.0")
    private double payCut;

    @NotNull(message = "allowance is mandatory")
    @DecimalMin("10000.0")
    private double allowance;

    @NotNull(message = "headOfFamily is mandatory")
    @DecimalMin("100000.0")
    private double headOfFamily;

    private Timestamp created_at;

    private Timestamp updated_at;

    public SalaryMatrixRequest(Long salaryMatrixId, int grade, double basicSalary, double payCut, double allowance, double headOfFamily) {
        this.salaryMatrixId = salaryMatrixId;
        this.grade = grade;
        this.basicSalary = basicSalary;
        this.payCut = payCut;
        this.allowance = allowance;
        this.headOfFamily = headOfFamily;
    }
}
