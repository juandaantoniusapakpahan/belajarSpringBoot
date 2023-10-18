package com.spring.next.activemq.domain.request;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SumTwoRequest {
    private List<Integer> numbers;
    private Integer target;
}
