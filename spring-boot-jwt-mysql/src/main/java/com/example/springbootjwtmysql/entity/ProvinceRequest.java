package com.example.springbootjwtmysql.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Province {
    @Id
    private String province_id;
    private String province;
}
