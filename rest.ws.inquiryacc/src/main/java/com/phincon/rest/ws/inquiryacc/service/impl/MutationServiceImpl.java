package com.phincon.rest.ws.inquiryacc.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.rest.ws.inquiryacc.model.dto.response.MutationResponseTemplate;
import com.phincon.rest.ws.inquiryacc.service.MutationService;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MutationServiceImpl implements MutationService {


    @Override
    public MutationResponseTemplate accountHistory(String accNUMBER, String accTTP, String strDATE, String endDATE, String strINDEX) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MutationResponseTemplate mutations = mapper.readValue(new File("src/main/resources/specification/Mutasi_response.json"), MutationResponseTemplate.class);
        return mutations;
    }
}
