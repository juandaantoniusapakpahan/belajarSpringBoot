package com.example.springbootjwtmysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultCostKing {
    private Query query;
    private Province results;
    private Status status;
}
