package com.example.springdatamysql.controller;


import com.example.springdatamysql.model.Product;
import com.example.springdatamysql.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping()
    public @ResponseBody Product addNewProduct(@RequestBody Product product){
        return productRepository.saveAndFlush(product);
    }

    @GetMapping(path = "/findByPrice")
    public @ResponseBody List<Product> findProduct(@RequestParam Double price, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByPrice(price,pageable);
    }

    @GetMapping(path = "/findByPriceSort")
    public  @ResponseBody List<Product> findByPriceSort(@RequestParam Double price, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortColumn){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortColumn).descending());
        return productRepository.findAllByPrice(price,pageable);
    }

    @GetMapping(path = "/findByPriceMinMax")
    public  @ResponseBody List<Product> findByPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortColumn){
        Pageable pageable = PageRequest.of(page, size,Sort.by(sortColumn).descending());
        return productRepository.findAllByPriceBetween(minPrice, maxPrice, pageable);
    }

    @GetMapping()
    public  @ResponseBody List<Product> findAll(){
        return  productRepository.findAll();
    }
}

