package com.example.springbootjwtmysql.controller;


import com.example.springbootjwtmysql.entity.*;
import com.example.springbootjwtmysql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequest productRequest){
        Product product = new Product(productRequest);
        Product product_result = productService.create(product);
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.CREATED.value(), product_result);
        return new ResponseEntity<>(responseTemplate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findByName(@RequestBody ProductFindByField productFindByField){
        Pageable pageable = PageRequest.of(productFindByField.getPage(), productFindByField.getSize());
        Page<Product> products = productService.findByName(productFindByField.getName(), pageable);
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(), products);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }

    @PutMapping("/updateById")
    public ResponseEntity<?> updateById(@RequestBody ProductUpdate productUpdate){
        Product product = new Product(productUpdate);
        Product product_result = productService.updateById(product);
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(), product_result);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }
    @DeleteMapping("/deleteById")
    public ResponseEntity<?> activeForSale(@RequestBody ProductFindByField productFindByField){
        Product product_result = productService.activeForSale(productFindByField.getProductId());
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(), product_result);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }

    @GetMapping("/findProductById")
    public ResponseEntity<?> findById(@RequestBody ProductFindByField productFindByField){
        Product product = productService.findById(productFindByField.getProductId());
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(), product);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }
}
