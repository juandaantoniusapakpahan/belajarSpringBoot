package com.example.payroll.service.impl;

import com.example.payroll.exception.GradeSalaryMatrixExistsException;
import com.example.payroll.exception.NoSuchSalaryMatrixException;
import com.example.payroll.model.SalaryMatrix;
import com.example.payroll.repository.SalaryMatrixRepository;
import com.example.payroll.service.SalaryMatrixService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class SalaryMatrixServiceImpl implements SalaryMatrixService {

    @Autowired SalaryMatrixRepository salaryMatrixRepository;

    public SalaryMatrix create(SalaryMatrix salaryMatrix){
        try{
            return salaryMatrixRepository.save(salaryMatrix);
        }catch (DataIntegrityViolationException e){
            throw new GradeSalaryMatrixExistsException("Grade number already exists");
        }
    }

    public void delete(Long id){
        try{
            salaryMatrixRepository.findById(id).get();
            salaryMatrixRepository.deleteById(id);
        }catch (NoSuchElementException e){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }

    }

    public SalaryMatrix update(Long id, SalaryMatrix salaryMatrix){
        try{
            salaryMatrixRepository.findById(id).get();
            salaryMatrix.setSalaryMatrixId(id);
            return salaryMatrixRepository.save(salaryMatrix);
        }catch(NoSuchElementException e){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }catch(DataIntegrityViolationException e){
            throw new GradeSalaryMatrixExistsException("Grade number already exists");
        }
    }

    public List<SalaryMatrix> findAll(int page, int size, String sortColumn){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
        return salaryMatrixRepository.findAll(pageable).stream().toList();
    }

    public SalaryMatrix findById(Long id){
        try {
            return salaryMatrixRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }
    }

    public SalaryMatrix findByGrade(int grade){
        SalaryMatrix salaryMatrix =salaryMatrixRepository.findByGrade(grade);

        if (salaryMatrix == null){
            throw new NoSuchSalaryMatrixException("no such salary matrix");
        }
        return salaryMatrix;
    }

}
