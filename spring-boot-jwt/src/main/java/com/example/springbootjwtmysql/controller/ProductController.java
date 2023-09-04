package com.example.springbootjwtmysql.controller;


import com.example.springbootjwtmysql.config.IAuthenticationFacade;
import com.example.springbootjwtmysql.entity.*;
import com.example.springbootjwtmysql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> create(@RequestBody ProductRequest productRequest){
        Authentication authentication = iAuthenticationFacade.getAuthentication();
        UserInfoDetails userInfo = (UserInfoDetails) authentication.getPrincipal();
        Product product = new Product(productRequest);
        Product product_result = productService.create(product, userInfo.getId());
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.CREATED.value(), product_result);
        return new ResponseEntity<>(responseTemplate, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findByName(@RequestBody ProductFindByField productFindByField){
        Pageable pageable = PageRequest.of(productFindByField.getPage(), productFindByField.getSize());
        Page<Product> products = productService.findByName(productFindByField.getName(), pageable);
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(), products);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }

    @GetMapping("/allUserProduct")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public  ResponseEntity<?> findAllUserProduct(@RequestBody ProductFindByField productFindByField){
        Authentication authentication = iAuthenticationFacade.getAuthentication();
        UserInfoDetails userInfo = (UserInfoDetails) authentication.getPrincipal();

        Pageable pageable = PageRequest.of(productFindByField.getPage(), productFindByField.getSize());
        Page<Product> products = productService.findAllByUser(userInfo.getId(),pageable);
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(), products);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }

    @PutMapping("/updateById")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> updateById(@RequestBody ProductUpdate productUpdate){
        Authentication authentication = iAuthenticationFacade.getAuthentication();
        UserInfoDetails userInfo = (UserInfoDetails) authentication.getPrincipal();
        Product product = new Product(productUpdate);
        Product product_result = productService.updateById(product, userInfo.getId());
        ResponseTemplate responseTemplate = new ResponseTemplate<>(HttpStatus.OK.value(), product_result);
        return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
    }
    @DeleteMapping("/deleteById")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> activeForSale(@RequestBody ProductFindByField productFindByField){
        Authentication authentication = iAuthenticationFacade.getAuthentication();
        UserInfoDetails userInfo = (UserInfoDetails) authentication.getPrincipal();

        Product product_result = productService.activeForSale(productFindByField.getProductId(),userInfo.getId());
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
