package com.example.payroll.repository;

import com.example.payroll.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAll(Pageable pageable);
    List<Employee> findByGrade(int grade, Pageable pageable);
    List<Employee> findByActive(boolean active);


    @Modifying
    @Query("update Employee emp set emp.active = :active where emp.employeeId = :employeeId")
    void updateById(@Param("active") boolean isActive, @Param("employeeId") Long id);

}
