package com.spring.next.activemq.service.impl;

import com.spring.next.activemq.domain.request.PalindromeRequest;
import com.spring.next.activemq.service.PalindromeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PalindromeServiceImpl implements PalindromeService {
    private Logger LOGGER = LoggerFactory.getLogger(PalindromeServiceImpl.class);

    @Override
    public boolean palindromeNumber(PalindromeRequest request) {
        String str = request.getNumber().toString();
        for (int i= 0; i< str.length()/2; i++){
            if(str.charAt(i) == str.charAt(str.length()-1-i)){
                LOGGER.info("is {} Palindrome?: {}", request.getNumber(), true);
                return true;
            }
        }
        LOGGER.info("is {} Palindrome?: {}", request.getNumber(), false);
        return false;
    }
}
