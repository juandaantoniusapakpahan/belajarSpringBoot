package com.example.payroll.service.impl;

import com.example.payroll.model.SalaryMatrix;
import com.example.payroll.repository.SalaryMatrixRepository;
import com.example.payroll.service.SalaryMatrixService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SalaryMatrixServiceImpl implements SalaryMatrixService {

    @Autowired SalaryMatrixRepository salaryMatrixRepository;

    public SalaryMatrix create(SalaryMatrix salaryMatrix){
        return salaryMatrixRepository.save(salaryMatrix);
    }

    public void delete(Long id){
        salaryMatrixRepository.findById(id).get();
        salaryMatrixRepository.deleteById(id);
    }

    public SalaryMatrix update(Long id, SalaryMatrix salaryMatrix){
        salaryMatrixRepository.findById(id).get();
        salaryMatrix.setSalaryMatrixId(id);
        return salaryMatrixRepository.save(salaryMatrix);
    }

    public List<SalaryMatrix> findAll(){
        return salaryMatrixRepository.findAll();
    }

    public SalaryMatrix findById(Long id){
        return salaryMatrixRepository.findById(id).get();
    }

}
