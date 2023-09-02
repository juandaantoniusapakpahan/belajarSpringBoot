package com.example.springbootasync.model;


import lombok.Data;

import java.util.List;

@Data
public class CalculatorRequest {
    private List<Double> numbers;
}
