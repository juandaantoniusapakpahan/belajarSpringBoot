package com.example.springbootjwtmysql.repository;


import com.example.springbootjwtmysql.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name like %?1%")
    Page<Product> findByName(String name, Pageable pageable);

//    @Query("SELECT p FROM Product p WHERE p.userInfo.id = ?1")
    Page<Product> findByUserInfoId(int userId, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p set p.activeForSale = ?1 WHERE p.productId = ?2 AND p.userInfo.id=?3")
    void updateActiveForSaleByProductId(Boolean activeForSale, Integer productId, Integer userId);


}
