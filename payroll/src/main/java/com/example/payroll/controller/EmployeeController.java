package com.example.payroll.controller;


import com.example.payroll.model.entity.Employee;
import com.example.payroll.model.request.EmployeeRequest;
import com.example.payroll.model.response.OutPutResponseTemp;
import com.example.payroll.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
//
//    HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.add("user-agent","Application");
//            HttpEntity<EmployeeRequest> entity = new HttpEntity<EmployeeRequest>(employeeRequest,headers);
//            log.info("entity={}",entity.getBody());
//            log.info("headers={}", entity.getHeaders());

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired private EmployeeService employeeService;

    @Value("${SERVICE.SECRET}")
    private String SERVICE_SECRET;


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody EmployeeRequest employee){
        Employee employee1 = employeeService.save(employee);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<Employee>(HttpStatus.CREATED.name(), employee1);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,@Valid @RequestBody EmployeeRequest employeeRequest){
        Employee employee = employeeService.updateById(id, employeeRequest);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<Employee>(HttpStatus.OK.name(), employee);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam int page, @RequestParam int size, @RequestParam String sortColumn){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
        Page<Employee> employees = employeeService.findAll(pageable);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<>(HttpStatus.OK.name(), employees);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Employee employee = employeeService.findById(id);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp(HttpStatus.OK.name(), employee);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }
    @GetMapping("/grade/{grade}")
    public ResponseEntity<?> findByGrade(@PathVariable int grade, @RequestParam int page, @RequestParam int size, @RequestParam String sortColumn){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
        List<Employee> employees = employeeService.findByGrade(grade, pageable);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<List<Employee>>(HttpStatus.OK.name(), employees);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> nonActive(@PathVariable Long id){
        Employee employee = employeeService.deleteById(id);
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<Employee>(HttpStatus.OK.name(), employee);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }

    @GetMapping("/nonActive")
    public ResponseEntity<?> findNonActive(){
        List<Employee> employees = employeeService.findNonActive();
        OutPutResponseTemp outPutResponseTemp = new OutPutResponseTemp<List<Employee>>(HttpStatus.OK.name(), employees);
        return new ResponseEntity<>(outPutResponseTemp, HttpStatus.OK);
    }

}
