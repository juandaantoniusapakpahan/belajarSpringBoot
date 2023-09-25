package com.phincon.rest.ws.inquiryacc.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.rest.ws.inquiryacc.model.dto.request.CCRequestBody;
import com.phincon.rest.ws.inquiryacc.model.dto.response.CCResponse;
import com.phincon.rest.ws.inquiryacc.service.CreditCartService;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CreditCardServiceImpl implements CreditCartService {
    @Override
    public CCResponse creditCard(CCRequestBody request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CCResponse response = mapper.readValue(new File("src/main/resources/specification/CreditCard_cardnumber_response.json"), CCResponse.class);
        return null;
    }
}
