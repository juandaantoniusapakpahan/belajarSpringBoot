package com.spring.next.activemq.controller;


import com.spring.next.activemq.domain.request.PalindromeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.spring.next.activemq.Constant.QUEUE_PALINDROME;

@RestController
public class PalindromeController {
    @Autowired
    private JmsTemplate jmsTemplate;


    @PostMapping("/palindrome")
    public void isPalindrome(@RequestBody PalindromeRequest request){
        jmsTemplate.convertAndSend(QUEUE_PALINDROME, request);
    }
}
