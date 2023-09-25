package com.phincon.rest.ws.inquiryacc.controller;

import com.phincon.rest.ws.inquiryacc.model.dto.request.CCRequestBody;
import com.phincon.rest.ws.inquiryacc.model.dto.response.CCResponse;
import com.phincon.rest.ws.inquiryacc.service.CreditCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/services/creditcard")
public class CreditCartController {

    @Autowired
    private CreditCartService creditCartService;
    @PostMapping
    public ResponseEntity<CCResponse> creditCard(
            @RequestHeader(value = "service") String service,
            @RequestHeader(value = "traceID") String traceID,
            @RequestHeader(value = "channel") String channel,
            @RequestHeader(value = "timestamp") String timestamp,
            @RequestBody CCRequestBody request) throws Exception
    {
        CCResponse ccResponse = creditCartService.creditCard(request);
        return new ResponseEntity<>(ccResponse, HttpStatus.OK);
    }
}
