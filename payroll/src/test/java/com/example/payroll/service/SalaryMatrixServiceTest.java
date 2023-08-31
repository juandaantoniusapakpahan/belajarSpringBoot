package com.example.payroll.service;

import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.entity.SalaryMatrix;
import com.example.payroll.model.request.SalaryMatrixRequest;
import com.example.payroll.repository.SalaryMatrixRepository;
import com.example.payroll.service.impl.SalaryMatrixServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SalaryMatrixServiceTest {

    @Mock
    SalaryMatrixRepository salaryMatrixRepository;

    @InjectMocks
    SalaryMatrixService salaryMatrixService = new SalaryMatrixServiceImpl();

    @BeforeEach
    void setMockOutPutSM(){
        List<SalaryMatrix> salaryMatrices = new ArrayList<>();
        salaryMatrices.add(new SalaryMatrix(1l, 1, 10000000, 10000000, 100000, 100000));
        salaryMatrices.add(new SalaryMatrix(2l, 2, 20000000, 20000000, 200000, 200000));
        salaryMatrices.add(new SalaryMatrix(3l, 3, 30000000, 30000000, 300000, 300000));
        salaryMatrices.add(new SalaryMatrix(4l, 4, 40000000, 40000000, 400000, 400000));

        lenient().when(salaryMatrixRepository.findAll(PageRequest.of(0, 3, Sort.by("salaryMatrixId").ascending()))).thenReturn(new PageImpl<>(salaryMatrices));
        lenient().when(salaryMatrixRepository.save(any())).thenReturn(
                salaryMatrices.get(0)
        );
        lenient().when(salaryMatrixRepository.findById(1l)).thenReturn(Optional.of(salaryMatrices.get(0)));
        lenient().when(salaryMatrixRepository.findByGrade(3)).thenReturn(Optional.of(salaryMatrices.get(2)));
    }

    @Test
    @Order(1)
    void setSave(){
        SalaryMatrixRequest salaryMatrixRequest = new SalaryMatrixRequest(
                0l,
                1, 10000000, 10000000, 100000, 100000
        );

        SalaryMatrix employee = salaryMatrixService.create(salaryMatrixRequest);
        assertNotNull(employee);
        assertThat(employee).usingRecursiveComparison().ignoringFields("salaryMatrixId").isEqualTo(salaryMatrixRequest);
    }

    @Test
    @Order(2)
    void setFindById(){
        SalaryMatrix salaryMatrix = salaryMatrixService.findById(1l);
        assertNotNull(salaryMatrix);
    }

    @Test
    @Order(3)
    void setFindByGrade(){
        SalaryMatrix salaryMatrix = salaryMatrixService.findByGrade(3);
        assertNotNull(salaryMatrix);
    }

    @Test
    @Order(4)
    // there is bug.
    void setFindAll(){
        Page<SalaryMatrix> salaryMatrixPage = salaryMatrixService.findAll(PageRequest.of(0, 3, Sort.by("salaryMatrixId").ascending()));
        assertEquals(4, salaryMatrixPage.getContent().size());
        assertEquals(4, salaryMatrixPage.getTotalElements());
    }

    @Test
    @Order(5)
    void setUpdate(){
        SalaryMatrixRequest salaryMatrixRequest = new SalaryMatrixRequest(
                0l,  1, 10000000, 10000000, 100000, 100000 );

        SalaryMatrix salaryMatrix = new SalaryMatrix(1l, 1, 10000000, 10000000, 100000, 100000);
        SalaryMatrix salaryMatrix1 = salaryMatrixService.update(1l, salaryMatrixRequest);
        assertThat(salaryMatrix1).usingRecursiveComparison().ignoringFields("salaryMatrixId").isEqualTo(salaryMatrixRequest);
    }
}
