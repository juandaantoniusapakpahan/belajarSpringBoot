package com.example.oauthtwologin.service;


import com.example.oauthtwologin.model.Product;
import com.example.oauthtwologin.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;

    public Product create(Product product){
        return productRepository.saveAndFlush(product);
    }
}
