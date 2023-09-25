package com.phincon.rest.ws.inquiryacc.controller;


import com.phincon.rest.ws.inquiryacc.model.dto.response.MutationResponseTemplate;
import com.phincon.rest.ws.inquiryacc.service.MutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/services/ACCTHST")
public class MutationController {

    @Autowired
    private MutationService mutationService;

    @GetMapping("/ACCTHST/{ACCTNBR}/{ACCTPP}/{STRDATE}/{ENDDATE}/{STRINDEX}")
    public ResponseEntity<MutationResponseTemplate> accHistory(
            @PathVariable(value = "ACCTNBR") String acctNBR,
            @PathVariable(value = "ACCTPP") String acctPP,
            @PathVariable(value = "STRDATE") String strDATE,
            @PathVariable(value = "ENDDATE") String endDATE,
            @PathVariable(value = "STRINDEX") String strINDEX
    ) throws Exception{
        MutationResponseTemplate accountHistory = mutationService.accountHistory(acctNBR, acctPP, strDATE, endDATE, strINDEX);
        return new ResponseEntity<>(accountHistory, HttpStatus.OK);
    }
}
