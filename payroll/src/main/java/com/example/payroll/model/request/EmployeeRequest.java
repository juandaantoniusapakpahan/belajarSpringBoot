package com.example.payroll.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class EmployeeRequest {

    @Size(min = 2, max = 64)
    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @Size(max = 64)
    private String lastName;

    @NotBlank(message = "gender is mandatory")
    private String gender;

    @NotNull(message = "grade is mandatory")
    private int grade;

    @NotBlank(message = "nip is mandatory")
    @Pattern(regexp = "\\d{8,20}")
    private String nip;

    @NotNull(message = "married is mandatory")
    private boolean married;

    private boolean active;

    private Timestamp created_at;

    private Timestamp updated_at;
}
