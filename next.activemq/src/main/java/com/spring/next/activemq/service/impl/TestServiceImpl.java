package com.spring.next.activemq.service.impl;

import com.spring.next.activemq.domain.Ticket;
import com.spring.next.activemq.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    private Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

    public void showTicket(Ticket ticket){
        LOGGER.info("Ticket: {}", ticket);
    }
}
