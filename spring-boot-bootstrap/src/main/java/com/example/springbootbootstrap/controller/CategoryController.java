package com.example.springbootbootstrap.controller;


import com.example.springbootbootstrap.exception.CategoryNotFoundException;
import com.example.springbootbootstrap.model.Book;
import com.example.springbootbootstrap.model.Category;
import com.example.springbootbootstrap.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired private CategoryRepository categoryRepository;

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody Category category){
        Category category1 = categoryRepository.save(category);
        return category1;
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable long id){
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
//
//    @GetMapping
//    public Category findByName(@RequestParam(value = "name") String name){
//        return categoryRepository.findByName(name);
//    }

}
