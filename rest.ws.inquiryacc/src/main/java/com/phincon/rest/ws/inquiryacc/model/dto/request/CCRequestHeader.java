package com.phincon.rest.ws.inquiryacc.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CCRequestHeader {
    private String service;
    private String traceID;
    private String channel;
    private String timestamp;
    private String listKey;
}
