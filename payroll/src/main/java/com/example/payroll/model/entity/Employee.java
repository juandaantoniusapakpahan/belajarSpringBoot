package com.example.payroll.model.entity;


import com.example.payroll.model.request.EmployeeRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Integer grade;

    @Column(nullable = false, unique = true, name = "nip", length = 64)
    private String nip;

    @Column(nullable = false,name = "married")
    private Boolean married;

    @Column(nullable = false,columnDefinition = "boolean default true")
    private Boolean active;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp created_at;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp updated_at;

    public Employee(EmployeeRequest emr){
        this.firstName = emr.getFirstName();
        this.lastName  = emr.getLastName();
        this.grade = emr.getGrade();
        this.gender =emr.getGender();
        this.nip = emr.getNip();
        this.married = emr.getMarried();
        this.active = emr.getActive();
    }

    public Employee(Long employeeId, String firstName, String lastName, String gender, Integer grade, String nip, Boolean married, Boolean active) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.grade = grade;
        this.nip = nip;
        this.married = married;
        this.active = active;
    }
}
