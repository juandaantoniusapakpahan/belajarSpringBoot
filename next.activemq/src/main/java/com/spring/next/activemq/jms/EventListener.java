package com.spring.next.activemq.jms;

import com.spring.next.activemq.domain.Ticket;
import com.spring.next.activemq.domain.request.PalindromeRequest;
import com.spring.next.activemq.domain.request.SumTwoRequest;
import com.spring.next.activemq.service.PalindromeService;
import com.spring.next.activemq.service.SumTwoService;
import com.spring.next.activemq.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.spring.next.activemq.Constant.*;

@Component
public class EventListener {

    @Autowired
    private TestService service;

    @Autowired
    private SumTwoService sumTwoService;

    @Autowired
    private PalindromeService palindromeService;

    @JmsListener(destination = QUEUE_TICKET,
            containerFactory = "defaultFactory")
    public void receiveMessage(Ticket ticket) {
        service.showTicket(ticket);
    }


    @JmsListener(destination = QUEUE_TWOSUM,containerFactory = "defaultFactory")
    public void consumeTwoSum(SumTwoRequest request){
        sumTwoService.sumTwo(request);
    }


    @JmsListener(destination = QUEUE_PALINDROME, containerFactory = "defaultFactory")
    public void isPalindrome(PalindromeRequest request){
        palindromeService.palindromeNumber(request);
    }
}
