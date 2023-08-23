package com.example.springbootbootstrap.repo;

import com.example.springbootbootstrap.model.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);
}
