package com.example.payroll.service.impl;

import com.example.payroll.exception.GradeSalaryMatrixExistsException;
import com.example.payroll.exception.NoSuchSalaryMatrixException;
import com.example.payroll.model.entity.SalaryMatrix;
import com.example.payroll.model.request.SalaryMatrixRequest;
import com.example.payroll.repository.SalaryMatrixRepository;
import com.example.payroll.service.SalaryMatrixService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;

@Service
@Transactional
public class SalaryMatrixServiceImpl implements SalaryMatrixService {

    @Autowired
    private SalaryMatrixRepository salaryMatrixRepository;

    @Override
    public SalaryMatrix create(SalaryMatrixRequest salaryMatrixRequest) throws RuntimeException{
        try{
            SalaryMatrix salaryMatrix = new SalaryMatrix(salaryMatrixRequest);
            return salaryMatrixRepository.save(salaryMatrix);
        }catch (DataIntegrityViolationException e){
            throw new GradeSalaryMatrixExistsException("Grade number already exists");
        }
    }
    @Override
    public void delete(Long id) throws RuntimeException{
        try{
            salaryMatrixRepository.findById(id).get();
            salaryMatrixRepository.deleteById(id);
        }catch (NoSuchElementException e){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }

    }

    @Override
    public SalaryMatrix update(Long id, SalaryMatrixRequest salaryMatrixRequest)throws RuntimeException{
        try{
            salaryMatrixRepository.findById(id).get();
            SalaryMatrix salaryMatrix = new SalaryMatrix(salaryMatrixRequest);
            salaryMatrix.setSalaryMatrixId(id);
            return salaryMatrixRepository.save(salaryMatrix);
        }catch(NoSuchElementException e){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }catch(DataIntegrityViolationException e){
            throw new GradeSalaryMatrixExistsException("Grade number already exists");
        }
    }

    @Override
    public Page<SalaryMatrix> findAll(Pageable pageable) throws RuntimeException{
        return salaryMatrixRepository.findAll(pageable);
    }

    @Override
    public SalaryMatrix findById(Long id) throws RuntimeException{
        try {
            return salaryMatrixRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }
    }

    @Override
    public SalaryMatrix findByGrade(int grade) throws  RuntimeException{
        try {
            SalaryMatrix salaryMatrix =salaryMatrixRepository.findByGrade(grade).get();
            return salaryMatrix;
        }catch (NoSuchElementException e){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }
    }

}
