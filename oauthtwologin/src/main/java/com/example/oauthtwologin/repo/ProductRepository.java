package com.example.oauthtwologin.repo;

import com.example.oauthtwologin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
