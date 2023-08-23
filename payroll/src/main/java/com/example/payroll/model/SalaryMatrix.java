package com.example.payroll.model;


import jakarta.persistence.*;
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
    private int grade;
    private double basicSalary;
    private double payCut;
    private double allowance;
    private double headOfFamily;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
}
