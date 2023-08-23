package com.example.springdatamysql.repo;

import com.example.springdatamysql.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPrice(double price, Pageable pageable);


    List<Product> findAllByPriceBetween(double minPrice, double maxPrice,Pageable pageable);
}
