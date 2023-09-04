package com.example.springbootjwtmysql.service;

import com.example.springbootjwtmysql.entity.Product;
import com.example.springbootjwtmysql.entity.UserInfo;
import com.example.springbootjwtmysql.repository.ProductRepository;
import com.example.springbootjwtmysql.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public Product create(Product product, int userId){
        UserInfo userInfo = userInfoRepository.findById(userId).get();
        product.setUserInfo(userInfo);
        return productRepository.save(product);
    }

    public Page<Product> findByName(String productName, Pageable pageable){
        return productRepository.findByName(productName,pageable);
    }

    public Product updateById(Product product, Integer userId){
        UserInfo userInfo = userInfoRepository.findById(userId).get();
        product.setUserInfo(userInfo);
        return productRepository.save(product);
    }

    public Page<Product> findAllByUser(Integer userId, Pageable pageable){
        return productRepository.findByUserInfoId(userId, pageable);
    }

    public Product activeForSale(Integer productId, Integer userId)
    {
        boolean activeForSale;
        if (productRepository.findById(productId).get().getActiveForSale()==true){
            activeForSale = false;
        }else{
            activeForSale = true;
        }
        productRepository.updateActiveForSaleByProductId(activeForSale, productId, userId);
        Product result = productRepository.findById(productId).get();
        result.setActiveForSale(activeForSale);
        return result;
    }

    public Product findById(Integer productId){
        return productRepository.findById(productId).get();
    }

}
