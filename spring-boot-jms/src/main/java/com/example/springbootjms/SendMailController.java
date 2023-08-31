package com.example.springbootjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailController {

    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping("/api/send-email/{destination}")
    public ResponseEntity<Email> sendEmail(@PathVariable(name = "destination") String destination, @RequestBody Email email) throws Exception{
        jmsTemplate.convertAndSend(destination, email);
        return ResponseEntity.ok(email);
    }
}
