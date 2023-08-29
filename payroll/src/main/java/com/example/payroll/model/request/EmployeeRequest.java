package com.example.payroll.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class EmployeeRequest {

    @JsonIgnore
    private Long EmployeeId;
    @Size(min = 2, max = 64)
    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @Size(max = 64)
    @NotBlank(message = "firstName is mandatory")
    private String lastName;

    @NotBlank(message = "gender is mandatory")
    private String gender;

    @NotNull(message = "grade is mandatory")
    private Integer grade;

    @NotBlank(message = "nip is mandatory")
    @Pattern(regexp = "\\d{8,20}")
    private String nip;

    @NotNull(message = "married is mandatory")
    private Boolean married;


    private Boolean active;

    private Timestamp created_at;

    private Timestamp updated_at;

    public EmployeeRequest( String firstName, String lastName, String gender, Integer grade, String nip, Boolean married, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.grade = grade;
        this.nip = nip;
        this.married = married;
        this.active = active;
    }
}
