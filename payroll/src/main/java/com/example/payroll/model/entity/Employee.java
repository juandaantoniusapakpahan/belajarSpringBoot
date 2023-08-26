package com.example.payroll.model.entity;


import com.example.payroll.model.request.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(nullable = false, length = 64)
    private String firstName;

    @Column(nullable = true, length = 64)
    private String lastName;

    @Column(nullable = false, length = 16)
    private String gender;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false, unique = true, name = "nip", length = 64)
    private String nip;

    @Column(nullable = false,name = "married")
    private boolean married;

    @Column(nullable = false,columnDefinition = "boolean default true")
    private boolean active;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp created_at;


    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp updated_at;

    public Employee(EmployeeRequest emr){
        this.firstName = emr.getFirstName();
        this.lastName  = emr.getLastName();
        this.gender =emr.getGender();
        this.nip = emr.getNip();
        this.married = emr.isMarried();
        this.active = emr.isActive();
    }
}
