package com.example.springdatamysql.model;


import jakarta.persistence.*;

@Entity
@Table(name = "foos")
public class Foos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public  Foos(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Foos(String name){
        this.name = name;
    }
}
