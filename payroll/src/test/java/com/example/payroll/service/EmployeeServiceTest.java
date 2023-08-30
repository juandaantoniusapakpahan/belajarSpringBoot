package com.example.payroll.service;


import com.example.payroll.exception.NoSuchEmployeeException;
import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.request.EmployeeRequest;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    SalaryMatrixService salaryMatrixService;

    @InjectMocks
    EmployeeService employeeService = new EmployeeServiceImpl();

    @BeforeEach
    void setMockOutPut(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee((long)1, "A","B","P",1,"98239",true,true));
        employees.add(new Employee((long)2, "B","A","P",1,"98234",true,true));
        employees.add(new Employee((long)3, "C","C","P",1,"98236",true,true));
        employees.add(new Employee((long)4, "D","D","P",1,"98234",true,true));
        lenient().when(employeeRepository.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(employees));
        lenient().when(employeeRepository.save(any()))
                .thenReturn(new Employee((long)4, "D","D","P",1,"98234",true,true));
    }

    @Test
    @Order(1)
    void setFindById(){
        lenient().when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee((long) 4, "D", "D", "P", 1, "98234", true, true))
        );
        Employee employee = employeeService.findById(any());
        assertEquals(4, employee.getEmployeeId());
    }

    @Test
    @Order(2)
    void setFindByGrade(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee((long)1, "A","B","P",1,"98239",true,true));
        employees.add(new Employee((long)2, "B","A","P",1,"98234",true,true));
        employees.add(new Employee((long)3, "C","C","P",1,"98236",true,true));
        lenient().when(employeeRepository.findByGrade(1, PageRequest.of(0, 10))).thenReturn(employees);
        List<Employee> employeeList = employeeService.findByGrade(1, PageRequest.of(0, 10));
        assertEquals(3, employeeList.size());
    }

    @Test
    @Order(3)
    void setFindByGradeNoData(){
        List<Employee> employees = new ArrayList<>();
        lenient().when(employeeRepository.findByGrade(10, PageRequest.of(0, 10))).thenReturn(employees);
        List<Employee> employeeList = employeeService.findByGrade(10, PageRequest.of(0, 10));
        assertThat(employeeList.isEmpty());
        assertEquals(new ArrayList<>(), employeeList);
        assertEquals(0, employeeList.size());
    }

    @Test
    @Order(4)
    void setDeleteByIdNotFound(){
        lenient().when(employeeRepository.findById(any())).thenThrow(new NoSuchEmployeeException(
                "no such employee exists"
        ));
        assertThrows( NoSuchEmployeeException.class, ()-> {
            employeeService.deleteById(any());
        });
    }

    @Test
    @Order(5)
    void setSave(){
        EmployeeRequest employeeRequest = new EmployeeRequest(
                "Juan",
                "Da",
                "Laki-laki",
                2,
                "2938434",
                true,
                true
        );
        Employee employee = new Employee(
                12l,
                "Juan",
                "Da",
                "Laki-laki",
                2,
                "2938434",
                true,
                true
        );


        lenient().when(employeeRepository.save(any())).thenReturn(employee);
        Employee employeeS = employeeService.save(employeeRequest);
        assertThat(employeeS).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(employee);
    }

    @Test
    @Order(6)
    void setFindAll(){
        // Total data employee is 4, can be seen in the setMockOutPut method above
        // In this case, I just want to get 2 data (size = 2)
        // But total of data (employees.getTotalElements()) still 4.
        Page<Employee> employees = employeeService.findAll(PageRequest.of(0, 2));
        assertNotNull(employees.getContent());
        assertNotEquals(2, employees.getContent().size());
        assertEquals(4, employees.getTotalElements());
    }

}
