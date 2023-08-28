package com.example.payroll.controller;


import com.example.payroll.service.SalaryMatrixService;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.StringContains;
import org.junit.Test;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




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
    public void whenUserControllerInjected_thenNotNull() throws Exception {
        assertThat(salaryMatrixController).isNotNull();
    }

    @Test
    public void whenGetAllRequestSalaryMatrix_thenCorrectResponse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salaryMatrix?page=0&size=2&sortColumn=grade")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void whenGetAllRequestSalaryMatrixWithoutParam_thenIncorrectResponse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salaryMatrix?page=0&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestSalaryMatrix_thenCorrectResponse() throws Exception{
        String salaryMatrix = "{\"grade\": 4,\"basicSalary\":10000000,\"payCut\":500000,"
                + "\"allowance\" : 500000,"
                + "\"headOfFamily\" : 10000000}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/salaryMatrix")
                        .content(salaryMatrix)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPutRequestSalaryMatrix_thenCorrectResponse() throws Exception{
        String salaryMatrix = "{\"grade\": 2,\"basicSalary\":10000000,\"payCut\":500000,"
                + "\"allowance\" : 500000,"
                + "\"headOfFamily\" : 20000000}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/salaryMatrix/5")
                .content(salaryMatrix)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }



}
