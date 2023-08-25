package com.example.payroll.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payrolls")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payRollId;

    @Column(nullable = false)
    private int attend;

    @Column(nullable = false)
    private int absent;

    private double basicSalary;

    private double payCut;

    @NotBlank(message = "period is mandatory")
    private String period;

    private double headOfFamily;

    private double allowance;

    private Long employeeId;

    private Double total;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public Payroll(int attend, int absent, String period, Long employeeId){
        this.attend =attend;
        this.absent = absent;
        this.period = period;
        this.employeeId = employeeId;
    }

}
