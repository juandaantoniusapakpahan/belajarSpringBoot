package com.example.springbootjwtmysql.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFindByField {
    private Integer productId;
    private String name;
    private Integer page;
    private Integer size;
    private String sortColumn;
    private Boolean activeForSale;
}
