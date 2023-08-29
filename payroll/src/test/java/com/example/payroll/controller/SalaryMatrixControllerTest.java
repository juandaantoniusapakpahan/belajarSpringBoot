package com.example.payroll.controller;


import com.example.payroll.exception.NoSuchEmployeeException;
import com.example.payroll.exception.NoSuchSalaryMatrixException;
import com.example.payroll.model.entity.SalaryMatrix;
import com.example.payroll.service.SalaryMatrixService;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;





@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SalaryMatrixController.class)
@AutoConfigureMockMvc
public class SalaryMatrixControllerTest {

    @MockBean
    private SalaryMatrixService salaryMatrixService;
    @Autowired
    SalaryMatrixController salaryMatrixController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenSalaryMatrixControllerInjected_thenNotNull() throws Exception {
        assertThat(salaryMatrixController).isNotNull();
    }

    @Order(1)
    @DisplayName("Testing SalaryMatrix: When get all request salary matrix")
    @Test
    public void whenGetAllRequestSalaryMatrix_thenCorrectResponse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salaryMatrix?page=0&size=2&sortColumn=grade")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Order(2)
    @DisplayName("Testing SalaryMatrix: When get all request salary matrix with out param")
    @Test
    public void whenGetAllRequestSalaryMatrixWithoutParam_thenIncorrectResponse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salaryMatrix?page=0&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Order(3)
    @DisplayName("Testing SalaryMatrix: When post request salary matrix")
    @Test
    public void whenPostRequestSalaryMatrix_thenCorrectResponse() throws Exception{
        String salaryMatrix = "{\"grade\": 4,\"basicSalary\":10000000,\"payCut\":500000,"
                + "\"allowance\" : 500000,"
                + "\"headOfFamily\" : 10000000}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/salaryMatrix")
                        .content(salaryMatrix)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print());

    }

    @Order(4)
    @DisplayName("Testing SalaryMatrix: When post request salary matrix with BadPayload")
    @Test
    public void whenPostSalaryMatrixBadPayload_thenCorrectResponse() throws Exception{
        String salaryMatrix = "{\"basicSalary\":10000000,\"payCut\":500000,"
                + "\"allowance\" : 500000,"
                + "\"headOfFamily\" : 10000000}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/salaryMatrix")
                        .content(salaryMatrix)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Order(5)
    @DisplayName("Testing SalaryMatrix: When put request salary matrix")
    @Test
    public void whenPutRequestSalaryMatrix_thenCorrectResponse() throws Exception{
        String salaryMatrix = "{\"grade\": 2,\"basicSalary\":10000000,\"payCut\":500000,"
                + "\"allowance\" : 500000,"
                + "\"headOfFamily\" : 20000000}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/salaryMatrix/5")
                .content(salaryMatrix)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());;
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Order(6)
    @DisplayName("Testing SalaryMatrix: When put request salary matrix bad payload")
    @Test
    public void whenPutSalaryMatrixBadPayload_thenCorrectResponse() throws Exception{
        String salaryMatrix = "{\"basicSalary\":10000000,\"payCut\":500000,"
                + "\"allowance\" : 500000,"
                + "\"headOfFamily\" : 20000000}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/salaryMatrix/5")
                        .content(salaryMatrix)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Order(7)
    @DisplayName("Testing SalaryMatrix: When get by id request")
    @Test
    public void whenGetByIdRequestSalaryMatrix_thenCorrectResponse() throws Exception{
        long id = 20;
        SalaryMatrix salaryMatrix = new SalaryMatrix(
                id,
                2,
                1000000.0,
                1000000.0,
                1000000.0,
                1000000.0
        );
        when(salaryMatrixService.findById(id)).thenReturn(salaryMatrix);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salaryMatrix/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Order(8)
    @DisplayName("Testing SalaryMatrix: When delete by id request salary matrix")
    @Test
    public void whenDeleteRequestSalaryMatrix_thenCorrectResponse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/salaryMatrix/{id}", 10))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(9)
    @DisplayName("Testing SalaryMatrix: When get by id request bad id")
    public void whenGetByIdRequestSalaryMatrixBadId_thenCorrectResponse() throws Exception{
        Mockito.doThrow(new NoSuchSalaryMatrixException("no such salary matrix")).when(salaryMatrixService).findById(any());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salaryMatrix/{id}", 23)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Testing Salary Matrix: When get by grade")
    @Order(10)
    public void whenGetByGradeRequest_thenCorrectResponse() throws Exception{
        long id = 20;
        SalaryMatrix salaryMatrix = new SalaryMatrix(
                id,
                2,
                1000000.0,
                1000000.0,
                1000000.0,
                1000000.0
        );
        when(salaryMatrixService.findByGrade(salaryMatrix.getGrade())).thenReturn(salaryMatrix);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salaryMatrix/grade/{grade}",2).
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Order(11)
    @DisplayName("Testing SalaryMatrix: When delete by id request salary matrix bad id")
    @Test
    public void whenDeleteRequestSalaryMatrixBadId_thenCorrectResponse() throws Exception{
        Mockito.doThrow(new NoSuchSalaryMatrixException("no such salary matrix")).when(salaryMatrixService).delete(any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/salaryMatrix/{id}", 10))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }



}
