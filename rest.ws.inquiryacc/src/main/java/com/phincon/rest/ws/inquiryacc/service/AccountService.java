package com.phincon.rest.ws.inquiryacc.service;



import com.phincon.rest.ws.inquiryacc.model.dto.response.InquiryAccountResponseTemplate;

public interface AccountService {

    InquiryAccountResponseTemplate inquiryAccount(String cifNumber) throws Exception;
}
