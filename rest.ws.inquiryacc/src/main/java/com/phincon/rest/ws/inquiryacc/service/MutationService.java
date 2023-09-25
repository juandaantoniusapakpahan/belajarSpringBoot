package com.phincon.rest.ws.inquiryacc.service;

import com.phincon.rest.ws.inquiryacc.model.dto.response.MutationResponseTemplate;

public interface MutationService {

    MutationResponseTemplate accountHistory(String accNUMBER, String accTTP, String strDATE, String endDATE, String strINDEX) throws Exception;
}
