package com.example.payroll.repository;


import com.example.payroll.model.entity.SalaryMatrix;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalaryMatrixRepositoryTest {

    @Autowired
    SalaryMatrixRepository salaryMatrixRepository;

    @AfterEach
    public void deleteAll(){
        salaryMatrixRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void setSave(){
        SalaryMatrix salaryMatrix = new SalaryMatrix(
          0l,
          1,
          1000000,
          1000000,
          1000000,
          1000000
        );

        SalaryMatrix result = salaryMatrixRepository.save(salaryMatrix);
        assertThat(result).usingRecursiveComparison().ignoringFields("salaryMatrixId").isEqualTo(salaryMatrix);

        salaryMatrixRepository.deleteAll();
        assertEquals(true,salaryMatrixRepository.findAll().isEmpty());
    }


    @Test
    @Order(2)
    public void setUpdate(){
        SalaryMatrix salaryMatrix = new SalaryMatrix(
                0l,
                1,
                1000000,
                1000000,
                1000000,
                1000000
        );

        SalaryMatrix resultInsert = salaryMatrixRepository.save(salaryMatrix);
        assertThat(resultInsert).usingRecursiveComparison().ignoringFields("salaryMatrixId").isEqualTo(salaryMatrix);

        SalaryMatrix salaryMatrixUpdate = new SalaryMatrix(
                resultInsert.getSalaryMatrixId(),
                1,
                200000,
                2000000,
                1000000,
                1000000
        );
        SalaryMatrix resultUpdate = salaryMatrixRepository.save(salaryMatrixUpdate);
        assertThat(resultUpdate).usingRecursiveComparison().isEqualTo(salaryMatrixUpdate);

        salaryMatrixRepository.deleteAll();
        assertEquals(true, salaryMatrixRepository.findAll().isEmpty());
    }

    @Test
    @Order(3)
    public  void setFindById(){
        SalaryMatrix salaryMatrix = new SalaryMatrix(
                0l,
                1,
                1000000,
                1000000,
                1000000,
                1000000
        );

        SalaryMatrix insertResult = salaryMatrixRepository.save(salaryMatrix);
        assertThat(insertResult).usingRecursiveComparison().ignoringFields("salaryMatrixId").isEqualTo(salaryMatrix);

        SalaryMatrix findByIdResult = salaryMatrixRepository.findById(insertResult.getSalaryMatrixId()).get();
        assertThat(findByIdResult).usingRecursiveComparison().isEqualTo(insertResult);

        salaryMatrixRepository.deleteAll();
        assertEquals(true, salaryMatrixRepository.findAll().isEmpty());
    }

    @Test
    @Order(4)
    public void setFindAll(){
       List<SalaryMatrix> salaryMatrices = new ArrayList<>();
       salaryMatrices.add( new SalaryMatrix(
               0l,
               10,
               1000000,
               1000000,
               1000000,
               1000000
       ));
       salaryMatrices.add( new SalaryMatrix(
               0l,
               12,
               2000000,
               2000000,
               2000000,
               2000000
       ));

       Iterable<SalaryMatrix> result_save =salaryMatrixRepository.saveAll(salaryMatrices);
       assertNotNull(result_save);

       List<SalaryMatrix> salaryMatricesList = salaryMatrixRepository.findAll();
       assertNotEquals(0, salaryMatricesList.size());

       salaryMatrixRepository.deleteAll();
       assertEquals(true, salaryMatrixRepository.findAll().isEmpty());
    }


}
