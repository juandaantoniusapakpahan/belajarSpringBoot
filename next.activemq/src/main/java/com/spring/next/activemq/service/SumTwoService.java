package com.spring.next.activemq.service;
import com.spring.next.activemq.domain.request.SumTwoRequest;

import java.util.List;

public interface SumTwoService {
    public List<Integer> sumTwo(SumTwoRequest request);
}
