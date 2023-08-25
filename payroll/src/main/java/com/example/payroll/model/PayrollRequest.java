package com.example.payroll.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayrollRequest {

    @NotNull(message = "attend is mandatory")
    @Min(0)
    private int absent;

    @NotNull(message = "attend is mandatory")
    @Min(0)
    private int attend;

    @NotBlank(message = "period is mandatory")
    private String period;

    @NotNull(message = "employeeId is mandatory")
    private Long employeeId;
}
