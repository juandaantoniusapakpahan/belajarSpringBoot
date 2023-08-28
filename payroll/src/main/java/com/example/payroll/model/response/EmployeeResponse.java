package com.example.payroll.model.response;


import com.example.payroll.model.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResponse {
    private Long employeeId;

    private String firstName;

    private String lastName;

    private String gender;

    private int grade;

    private String nip;

    private boolean married;

    private boolean active;

    public EmployeeResponse(Employee employee){
        this.employeeId = employee.getEmployeeId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.gender = employee.getGender();
        this.grade = employee.getGrade();
        this.nip = employee.getNip();
        this.married = employee.isMarried();
        this.active = employee.isActive();
    }
}
