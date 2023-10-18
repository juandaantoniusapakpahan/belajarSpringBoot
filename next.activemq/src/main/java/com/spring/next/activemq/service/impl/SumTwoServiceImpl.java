package com.spring.next.activemq.service.impl;


import com.spring.next.activemq.domain.request.SumTwoRequest;
import com.spring.next.activemq.service.SumTwoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SumTwoServiceImpl implements SumTwoService {

    private Logger LOGGER = LoggerFactory.getLogger(SumTwoServiceImpl.class);

    public List<Integer> sumTwo(SumTwoRequest request){
        List<Integer> subResult = new ArrayList<>();
        // [1,2,3,4,5]
        // target = 5
        for(Integer i = 0; i< request.getNumbers().size()/2; i++){
            int number = request.getTarget() - request.getNumbers().get(i);
            if (subResult.contains(request.getNumbers().get(i))){
                LOGGER.info("SUM TWO: {}", request);
                return Arrays.asList(subResult.indexOf(request.getNumbers().get(i)), i);

            }else{
                subResult.add(number);
            }
        }
        LOGGER.info("SUM TWO: {}", request);
        return subResult;
    }
}
