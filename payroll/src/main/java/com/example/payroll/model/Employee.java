package com.example.payroll.model;


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
    @Size(min = 2, max = 64)
    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @Column(nullable = true, length = 64)
    @Size(max = 64)
    private String lastName;

    @Column(nullable = false, length = 16)
    @NotBlank(message = "gender is mandatory")
    private String gender;

    @Column(nullable = false)
    @NotNull(message = "grade is mandatory")
    private int grade;

    @Column(nullable = false, unique = true, name = "nip")
    @NotBlank(message = "nip is mandatory")
    @Pattern(regexp = "\\d{8,20}")
    private String nip;

    @Column(nullable = false,name = "married")
    @NotNull(message = "married is mandatory")
    private boolean married;

    @Column(nullable = false,columnDefinition = "boolean default true")
    private boolean active;

    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
}
