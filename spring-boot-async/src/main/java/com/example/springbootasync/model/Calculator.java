package com.example.springbootasync.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Calculator {
    private Double min;
    private Double max;
    private Double addition;
    private Double subtraction;
    private Double multiplication;
    private Double distribution;
}
