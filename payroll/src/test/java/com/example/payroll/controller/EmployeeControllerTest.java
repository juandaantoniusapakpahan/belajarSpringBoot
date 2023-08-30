package com.example.payroll.controller;


import com.example.payroll.exception.NoSuchEmployeeException;
import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.request.EmployeeRequest;
import com.example.payroll.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @MockBean private EmployeeService employeeService;
    @Autowired EmployeeController employeeController;

    @Autowired private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Testing Employee: Employee Controller")
    public void whenEmployeeControllerInjected_thenNotNull() throws Exception {
        assertThat(employeeController).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("Testing Employee: When get All request employee")
    public void whenGetAllEmployeeRequest_thenReturnCorrectResponse() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees?page=0&size=5&sortColumn=nip")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    @DisplayName("Testing Employee: When post request employee")
    public void whenPostEmployeeRequest_thenReturnCorrectResponse() throws Exception{
        String employee = "{\"firstName\": \"Julio\", \"lastName\":\"Panjaita\",\"gender\":\"Laki-laki\",\"grade\":1" +
                ",\"nip\":\"1297034231\",\"married\":true}";

        EmployeeRequest employeeRequest = new ObjectMapper().readValue(employee, new TypeReference<EmployeeRequest>(){});
        Employee employee1 = new ObjectMapper().readValue(employee, new TypeReference<Employee>(){});
        when(employeeService.save(employeeRequest)).thenReturn(employee1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Julio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value("Panjaita"));
    }

    @Test
    @Order(4)
    @DisplayName("Testing Employee: When post request employee bad payload")
    public void whenPostEmployeeBadPayload_thenCorrectResponse() throws Exception{
        String employee = "{\"lastName\":\"Panjaita\",\"gender\":\"Laki-laki\",\"grade\":1" +
                ",\"nip\":\"1297034231\",\"married\":true}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(5)
    @DisplayName("Testing Employee: When put request employee")
    public void whenPutEmployeeRequest_thenCorrectResponse() throws Exception{
        String employee = "{\"firstName\": \"Julio\", \"lastName\":\"Yermia Panjaitan\",\"gender\":\"Laki-laki\",\"grade\":1" +
                ",\"nip\":\"1297034231\",\"married\":true}";
        EmployeeRequest employeeRequest = new ObjectMapper().readValue(employee, new TypeReference<EmployeeRequest>(){});
        Employee employee1 = new ObjectMapper().readValue(employee, new TypeReference<Employee>(){});
        when(employeeService.updateById((long)10, employeeRequest)).thenReturn(employee1);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}",10)
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Julio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value("Yermia Panjaitan"));
    }

    @Test
    @Order(6)
    @DisplayName("Testing Employee: When put request employee bad payload")
    public void  whenPutEmployeeWithBadPayload_thenCorrectResponse() throws Exception{
        String employee = "{\"lastName\":\"Yermia Panjaitan\",\"gender\":\"Laki-laki\",\"grade\":1" +
                ",\"nip\":\"1297034231\",\"married\":true}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/2")
                        .content(employee)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(7)
    @DisplayName("Testing Employee: When put request employee")
    public void whenGetByIdEmployee_thenCorrectResponse() throws Exception{
        long id = 23;
        Employee employee = new Employee(id, "Julio","Panjaitan","Laki-laki",2, "1297034231",true,false);
        when(employeeService.findById(id)).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}",id))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value(employee.getLastName()));
    }

    @Test
    @Order(8)
    @DisplayName("Testing Employee: When non active by id request employee")
    public void  whenNonActiveByIdRequest_thenCorrectResponse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/employees/{id}",10))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(9)
    @DisplayName("Testing Employee: When get by grade request employee")
    public void  whenGetByGradeRequestEmployee() throws Exception{
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee((long)10, "Julio","Panjaitan","Laki-laki",2, "1297034231",true,true);
        Employee employee2 = new Employee((long)12, "Julio","Panjaitan","Laki-laki",2, "1297434231",true,true);
        employees.add(employee);
        employees.add(employee2);
        Pageable pageable = PageRequest.of(1,5,  Sort.by("nip"));
        when(employeeService.findByGrade(2, pageable)).thenReturn(employees);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/grade/{grade}?page=0&size=5&sortColumn=nip",2))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @Order(10)
    @DisplayName("Testing Employee: when get Non Active request employee")
    public void whenGetNonActiveRequestEmployee() throws Exception{
        long id = 23;
        long idI = 24;
        Employee employee = new Employee(id, "Julio","Panjaitan","Laki-laki",2, "1297034231",true,false);
        Employee employeeI = new Employee(idI, "Karunia","Sentosa","Laki-laki",2, "12970343231",true,false);
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeI);
        employees.add(employee);

        when(employeeService.findNonActive()).thenReturn(employees);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/nonActive"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray());
    }

    @Test
    @Order(11)
    @DisplayName("Testing Employee: When get by id request and id not found")
    public void whenGetByIdRequestEmployeeBadId() throws Exception{
        Mockito.when(employeeService.findById(any())).thenThrow(new NoSuchEmployeeException("no such employee"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}",92)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("no such employee"));
    }

    @Test
    @Order(12)
    @DisplayName("Test Employee: When non active by id request and id not found")
    public void whenNonActiveByIdRequestEmployeeBadId() throws Exception{
        Mockito.when(employeeService.deleteById((long)10)).thenThrow(new NoSuchEmployeeException("no such employee"));
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/employees/{id}", (long)10).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("no such employee"));
    }

}
