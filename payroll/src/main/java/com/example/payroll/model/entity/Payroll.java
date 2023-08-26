package com.example.payroll.model.entity;


import com.example.payroll.model.request.PayrollRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @Column(nullable = false)

    private double basicSalary;

    @Column(nullable = false)

    private double payCut;

    @Column(unique = true, nullable = false)
    private String period;

    @Column(nullable = false)
    private double headOfFamily;

    @Column(nullable = false)
    private double allowance;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private Double total;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //hide field as json
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //hide field as json
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp updatedAt;

    public Payroll(PayrollRequest payrollRequest){
        this.attend =payrollRequest.getAttend();
        this.absent = payrollRequest.getAbsent();
        this.period = payrollRequest.getPeriod();
        this.employeeId = payrollRequest.getEmployeeId();
    }

}
