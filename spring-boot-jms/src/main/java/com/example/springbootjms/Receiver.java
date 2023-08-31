package com.example.springbootjms;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    @JmsListener(destination = "mailbox121")
    public void receiveMessage1(Email email){
        log.info("Received <" +email+ ">");
    }

    @JmsListener(destination = "mailbox122")
    public void receiveMessage2(Email email){
        log.info("Received <" +email+ ">");
    }

    @JmsListener(destination = "mailbox123")
    public void receiveMessage3(Email email){
        log.info("Received <" +email+ ">");
    }
}
