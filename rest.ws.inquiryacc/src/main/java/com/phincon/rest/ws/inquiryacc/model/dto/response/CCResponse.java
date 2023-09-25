package com.phincon.rest.ws.inquiryacc.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CCResponse {
    private CCResponseHeader rsHeader;
    private List<CCResponseData> rsBody;
}
