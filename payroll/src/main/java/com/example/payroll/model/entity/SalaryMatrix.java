package com.example.payroll.model.entity;


import com.example.payroll.model.request.SalaryMatrixRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "salaryMatrixs")
public class SalaryMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_matrix_id")
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

    //Trigger function
//    db_payroll=# CREATE FUNCTION update_updated_at_column() RETURNS trigger
//    LANGUAGE plpgsql
//    AS $$
//    BEGIN
//    NEW.updated_at = NOW();
//    RETURN NEW;
//    END;
//    $$;

    // Make trigger
    //db_payroll=# CREATE TRIGGER sm_updated_sm_modtime BEFORE UPDATE ON salary_matrixs FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

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
