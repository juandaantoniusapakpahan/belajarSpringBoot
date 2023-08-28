package com.example.payroll.model.entity;
import com.example.payroll.model.request.PayrollRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payrolls", uniqueConstraints = {@UniqueConstraint(columnNames = {"period","employee_id"})})
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_roll_id")
    private Long payRollId;

    @Column(nullable = false)
    private int attend;

    @Column(nullable = false)
    private int absent;

    @Column(nullable = false)
    private double basicSalary;

    @Column(nullable = false)
    private double payCut;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private double headOfFamily;

    @Column(nullable = false)
    private double allowance;

//    @Column(nullable = false)
//    private Long employeeId;

    @Column(nullable = false)
    private Double total;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Employee employee;


    //Trigger function
//    db_payroll=# CREATE FUNCTION update_updated_at_column() RETURNS trigger
//    LANGUAGE plpgsql
//    AS $$
//    BEGIN
//    NEW.updated_at = NOW();
//    RETURN NEW;
//    END;
//    $$;

    // Make trigger
    //db_payroll=# CREATE TRIGGER sm_updated_sm_modtime BEFORE UPDATE ON salary_matrixs FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp updatedAt;

    public Payroll(PayrollRequest payrollRequest){
        this.attend =payrollRequest.getAttend();
        this.absent = payrollRequest.getAbsent();
        this.period = payrollRequest.getPeriod();
//        this.employeeId = payrollRequest.getEmployeeId();
    }

}
