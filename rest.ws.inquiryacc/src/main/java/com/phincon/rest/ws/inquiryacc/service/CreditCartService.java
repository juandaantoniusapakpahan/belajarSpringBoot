package com.phincon.rest.ws.inquiryacc.service;


import com.phincon.rest.ws.inquiryacc.model.dto.request.CCRequestBody;
import com.phincon.rest.ws.inquiryacc.model.dto.response.CCResponse;

public interface CreditCartService {
    CCResponse creditCard(CCRequestBody request) throws Exception;
}
