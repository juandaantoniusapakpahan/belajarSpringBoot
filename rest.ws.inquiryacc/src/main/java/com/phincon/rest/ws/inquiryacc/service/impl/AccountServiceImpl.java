package com.phincon.rest.ws.inquiryacc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.rest.ws.inquiryacc.model.dto.response.InquiryAccountResponseTemplate;
import com.phincon.rest.ws.inquiryacc.service.AccountService;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class AccountServiceImpl implements AccountService {


    @Override
    public InquiryAccountResponseTemplate inquiryAccount(String cifNumber) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InquiryAccountResponseTemplate inquiryAccountResponse = mapper.readValue(new File("src/main/resources/specification/InquiryAccount_response.json"), InquiryAccountResponseTemplate.class);
        return inquiryAccountResponse;
    }
}
