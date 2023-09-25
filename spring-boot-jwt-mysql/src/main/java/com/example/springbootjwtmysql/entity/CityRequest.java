package com.example.springbootjwtmysql.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    private String city_id;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "province_id")
    private Province province;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String city_name;

    @Column(nullable = false)
    private String postal_code;
}
