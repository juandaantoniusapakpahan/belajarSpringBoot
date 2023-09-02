package com.example.springbootjwtmysql.service;

import com.example.springbootjwtmysql.entity.Product;
import com.example.springbootjwtmysql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Page<Product> findByName(String productName, Pageable pageable){
        return productRepository.findByName(productName,pageable);
    }

    public Product updateById(Product product){
        return productRepository.save(product);
    }

    public Product activeForSale(Integer productId)
    {
        boolean activeForSale;
        if (productRepository.findById(productId).get().getActiveForSale()==true){
            activeForSale = false;
        }else{
            activeForSale = true;
        }
        productRepository.updateActiveForSaleByProductId(activeForSale, productId);
        Product result = productRepository.findById(productId).get();
        result.setActiveForSale(activeForSale);
        return result;
    }

    public Product findById(Integer productId){
        return productRepository.findById(productId).get();
    }

}
