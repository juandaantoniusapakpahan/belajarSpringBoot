package com.example.oauthtwologin.controller;


import com.example.oauthtwologin.model.Product;
import com.example.oauthtwologin.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired private ProductService productService;


    @PostMapping(path = "/products")
    public @ResponseBody Product create(@Valid @RequestBody Product product){
        return productService.create(product);
    }
}
