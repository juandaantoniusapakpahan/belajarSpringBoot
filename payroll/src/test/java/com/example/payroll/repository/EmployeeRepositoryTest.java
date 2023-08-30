package com.example.payroll.repository;

import com.example.payroll.model.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @AfterEach
    public void deleteAll(){
        employeeRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testCreateReadDelete() {

        Employee employee = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349234",
                true,
                true
        );

        // Save Employee
        Employee result = employeeRepository.save(employee);
        assertNotNull(result);
        assertNotNull(result.getEmployeeId());
        assertThat(result).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(employee);
        // Find ById
        Employee findEmployee = employeeRepository.findById(result.getEmployeeId()).get();
        assertNotNull(findEmployee);
        assertEquals(findEmployee.getEmployeeId(), result.getEmployeeId());

        employeeRepository.deleteAll();
        assertThat(employeeRepository.findById(result.getEmployeeId()).isEmpty());
    }

    @Test
    @Order(2)
    public void testCreateUpdateFindDelete() {

        Employee employee = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349234",
                true,
                true
        );

        // Save Employee
        Employee resultInsert = employeeRepository.save(employee);
        assertNotNull(resultInsert);

        // Payload
        Employee employeeUpdate = new Employee(
                resultInsert.getEmployeeId(),
                "Sentosa",
                "Karunia Mada",
                "Laki-laki",
                2,
                "2932349234",
                true,
                true
        );

        // Update
        Employee resultUpdate = employeeRepository.save(employeeUpdate);
        assertThat(resultUpdate).usingRecursiveComparison().isEqualTo(employeeUpdate);
        assertEquals(resultUpdate.getLastName(), employeeUpdate.getLastName());
        assertNotNull(employeeUpdate);

        // Delete All
        employeeRepository.deleteAll();
        assertThat(employeeRepository.findById(resultUpdate.getEmployeeId()));
    }

    @Test
    @Order(3)
    public void testCreateThrowException() {
        Employee employee = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349234",
                true,
                true
        );

        // Save Employee
        Employee resultInsert = employeeRepository.save(employee);
        assertNotNull(resultInsert);

        // Insert Double
        assertThrows(DataIntegrityViolationException.class, () -> {
            employeeRepository.save(employee);
        });
    }

    @Test
    @Order(4)
    public void testFindAll() {
        Employee employee = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349234",
                true,
                true
        );
        Employee employeeI = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349232",
                true,
                true
        );

        // Save
        Employee result1 = employeeRepository.save(employee);
        Employee result2 = employeeRepository.save(employeeI);
        assertThat(result1).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(employee);
        assertThat(result2).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(employeeI);


        Pageable pageable = PageRequest.of(0, 10);
        Page pageAll = employeeRepository.findAll(pageable);
        assertNotNull(pageAll.getContent().size());

        employeeRepository.deleteAll();
        assertEquals(0, employeeRepository.findAll(pageable).getTotalElements());
    }

    @Test
    @Order(5)
    public void testFindByGrade() {
        Employee employee = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349234",
                true,
                true
        );
        Employee employeeI = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349232",
                true,
                true
        );
        // Save
        Employee result1 = employeeRepository.save(employee);
        Employee result2 = employeeRepository.save(employeeI);
        assertThat(result1).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(employee);
        assertThat(result2).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(employeeI);

        Pageable pageable = PageRequest.of(0, 10);
        List<Employee> employees = employeeRepository.findByGrade(2, pageable);
        assertNotNull(employees);

        employeeRepository.deleteAll();
        assertEquals(0, employeeRepository.findAll(pageable).getTotalElements());
    }

    @Test
    @Order(6)
    public void testingFindNonActive(){
        Employee employee = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349234",
                true,
                false
        );
        Employee employeeI = new Employee(
                0l,
                "Sentosa",
                "Karunia",
                "Laki-laki",
                2,
                "2932349232",
                true,
                false
        );
        // Save
        Employee result1 = employeeRepository.save(employee);
        Employee result2 = employeeRepository.save(employeeI);
        assertThat(result1).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(employee);
        assertThat(result2).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(employeeI);

        List<Employee> employees = employeeRepository.findByActive(false);
        assertNotNull(employees);

        employeeRepository.deleteAll();
        assertThat(employeeRepository.findAll(PageRequest.of(0, 10000)).getContent().equals(0));
    }
}
