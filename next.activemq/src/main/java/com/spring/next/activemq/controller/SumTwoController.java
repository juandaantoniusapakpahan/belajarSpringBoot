package com.spring.next.activemq.controller;


import com.spring.next.activemq.domain.request.SumTwoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.spring.next.activemq.Constant.QUEUE_TWOSUM;

@RestController
public class SumTwoController {


    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/sum")
    public void twoSum(@RequestBody SumTwoRequest request){
        jmsTemplate.convertAndSend(QUEUE_TWOSUM, request);
    }
}
