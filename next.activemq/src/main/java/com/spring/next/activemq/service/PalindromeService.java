package com.spring.next.activemq.service;

import com.spring.next.activemq.domain.request.PalindromeRequest;

public interface PalindromeService {
    public boolean palindromeNumber(PalindromeRequest request);
}
