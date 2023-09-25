package com.phincon.rest.ws.inquiryacc.model.dto.response;

import com.phincon.rest.ws.inquiryacc.model.dto.request.CCRequestHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CCResponseHeader {
    private CCRequestHeader rqHeader;
    private String timeStamp;
    private String status;
    private String statusDesc;
    private int listCount;
    private String listKey;
}
