package com.example.payroll.controller;


import com.example.payroll.exception.NoSuchEmployeeException;
import com.example.payroll.exception.NoSuchPayrollException;
import com.example.payroll.exception.NoSuchSalaryMatrixException;
import com.example.payroll.model.entity.Payroll;
import com.example.payroll.service.PayrollService;
import org.junit.Test;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(controllers = PayrollController.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PayrollControllerTest {

    @MockBean
    private PayrollService payrollService;
    @Autowired
    private PayrollController payrollController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Testing Payroll: Payroll Controller")
    public void whenPayrollControllerInjected_thenNotNull() throws Exception {
        assertThat(payrollController).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("Testing Payroll: When post request payroll")
    public void whenPostRequestPayroll_thenCorrectResponse() throws  Exception{
        String payroll = "{\"absent\": 1, \"attend\":19, \"period\": \"July-2023\", \"employeeId\":1}";
        long userId = 1;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/payrolls")
                .content(payroll).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    @Order(3)
    @DisplayName("Testing Payroll: When post request payroll bad payload")
    public void whenPostRequestPayrollBadPayload_thenCorrectResponse() throws  Exception{
        String payroll = "{ \"attend\":19, \"period\": \"July-2023\", \"employeeId\":1}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/payrolls")
                        .content(payroll).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(4)
    @DisplayName("Testing Payroll: When put request payroll")
    public void whenPutRequestPayroll_thenCorrectResponse() throws Exception{
        long id = 10;
        String payroll = "{\"absent\": 1, \"attend\":19, \"period\": \"July-2023\", \"employeeId\":1}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/payrolls/{id}",id)
                        .content(payroll).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(5)
    @DisplayName("Testing Payroll: When put request payroll bad payload")
    public void whenPutRequestPayrollBadPayload_thenCorrectResponse() throws Exception{
        String payroll = "{ \"attend\":19, \"period\": \"July-2023\", \"employeeId\":1}";
        long id = 10;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/payrolls/{id}", id)
                .content(payroll).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(6)
    @DisplayName("Testing Payroll: When get all request payroll")
    public void  whenGetAllPayroll_thenCorrectResponse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/payrolls?page=0&size=10&sortColumn=payRollId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(7)
    @DisplayName("Testing Payroll: when get by id request payroll")
    public void  whenGetByIdPayroll_thenCorrectResponse() throws Exception{
        long payrollId= 10;

        Payroll payroll = new Payroll(
                payrollId,
                18,
                2,
                1000000.0,
                10000000.0,
                "Juny-2023",
                10000000.0,
                10000000.0,
                100000000.0
        );
        when(payrollService.findById(payrollId)).thenReturn(payroll);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/payrolls/{id}", payrollId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());;
    }

    @Test
    @Order(8)
    @DisplayName("Testing payroll: When get by employee id")
    public void whenGetByEmployeeIdRequestPayroll_thenCorrectResponse() throws Exception{

        Payroll payroll = new Payroll(
                (long) 10,
                18,
                2,
                1000000.0,
                10000000.0,
                "Juny-2023",
                10000000.0,
                10000000.0,
                100000000.0
        );
        Payroll payroll2 = new Payroll(
                (long) 12,
                18,
                2,
                1000000.0,
                10000000.0,
                "Juny-2023",
                10000000.0,
                10000000.0,
                100000000.0
        );

        List<Payroll> payrolls = new ArrayList<>();
        payrolls.add(payroll2);
        payrolls.add(payroll);

        when(payrollService.findByEmployeeId((long)102)).thenReturn(payrolls);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/payrolls/employee/122"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @Order(9)
    @DisplayName("Testing payroll: When delete by id request")
    public void whenDeleteRequestPayroll_thenCorrectResponse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/payrolls/{id}",10))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(9)
    @DisplayName("Testing payroll: When delete by id request bad id")
    public void whenDeleteRequestPayrollBadId_thenCorrectResponse() throws Exception{
        Mockito.doThrow(new NoSuchPayrollException("no such payroll")).when(payrollService).deleteById((long)10);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/payrolls/{id}",10))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(10)
    @DisplayName("Testing Payroll: when get by id request payroll bad id")
    public void  whenGetByIdPayrollBadId_thenCorrectResponse() throws Exception{

        Mockito.doThrow(new NoSuchPayrollException("no such payroll")).when(payrollService).findById(any());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/payrolls/{id}", 102)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());;
    }

    @Test
    @Order(11)
    @DisplayName("Testing Payroll: When post request with bad employee id ")
    public void whenPostRequestBadEmployeeId_thenCorrectResponse() throws Exception{
        String payroll = "{\"absent\": 1, \"attend\":19, \"period\": \"July-2023\", \"employeeId\":1}";
        Mockito.doThrow(new NoSuchEmployeeException("employee not found")).when(payrollService).save(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/payrolls")
                .content(payroll)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").
                        value("employee not found"));
    }

    @Test
    @Order(11)
    @DisplayName("Testing Payroll: When post request with bad grade number")
    public void whenPostRequestBadGradeNumber_thenCorrectResponse() throws Exception{
        String payroll = "{\"absent\": 1, \"attend\":19, \"period\": \"July-2023\", \"employeeId\":1}";
        Mockito.doThrow(new NoSuchSalaryMatrixException("no such salary matrix")).when(payrollService).save(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/payrolls")
                        .content(payroll)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").
                        value("no such salary matrix"));
    }

}
