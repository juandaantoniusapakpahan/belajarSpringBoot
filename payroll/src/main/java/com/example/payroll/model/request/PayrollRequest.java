package com.example.payroll.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayrollRequest {

    @Min(value = 0)
    @NotNull(message = "attend is mandatory")
    private Integer absent;

    @Min(value = 0)
    @NotNull(message = "attend is mandatory")
    private Integer attend;

    @NotBlank(message = "period is mandatory")
    private String period;

    @NotNull(message = "employeeId is mandatory")
    private Long employeeId;

    public PayrollRequest(Integer absent, Integer attend, String period, Long employeeId) {
        this.absent = absent;
        this.attend = attend;
        this.period = period;
        this.employeeId = employeeId;
    }
}
