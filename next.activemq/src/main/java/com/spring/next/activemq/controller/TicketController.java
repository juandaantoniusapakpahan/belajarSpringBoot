package com.spring.next.activemq.controller;


import com.spring.next.activemq.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.spring.next.activemq.Constant.QUEUE_TICKET;

@RestController
public class TicketController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping(value = "/buy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void buyTicket(@RequestBody Ticket ticket){
        jmsTemplate.convertAndSend(QUEUE_TICKET,new Ticket(ticket.getName(), ticket.getPrice(), ticket.getQuantity()));
    }
}
