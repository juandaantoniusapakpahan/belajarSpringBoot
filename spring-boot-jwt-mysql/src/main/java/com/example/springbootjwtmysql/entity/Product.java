package com.example.springbootjwtmysql.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    private Boolean activeForSale;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo;

    public Product(ProductRequest pr){
        this.name = pr.getName();
        this.description= pr.getDescription();
        this.activeForSale = pr.getActiveForSale();
    }

    public Product(ProductUpdate pu){
        this.productId = pu.getProductId();
        this.name =pu.getName();
        this.description = pu.getDescription();
        this.activeForSale = pu.getActiveForSale();
    }
}
