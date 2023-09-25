package com.phincon.rest.ws.inquiryacc.controller;

import com.phincon.rest.ws.inquiryacc.model.dto.response.InquiryAccountResponseTemplate;
import com.phincon.rest.ws.inquiryacc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/web/services/INQACCT")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/INQACCT/{cifnbr}")
    public ResponseEntity<InquiryAccountResponseTemplate> inquiryAccount(@PathVariable(value = "cifnbr") String cifNumber) throws Exception{
        InquiryAccountResponseTemplate inqAccount = accountService.inquiryAccount(cifNumber);
        return new ResponseEntity<>(inqAccount, HttpStatus.OK);

    }
}
