package com.phincon.rest.ws.inquiryacc.model.dto.response;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryAccountResponseTemplate {

    @JsonProperty("COUNT")
    private String COUNT;

    @JsonProperty("RESP")
    private String RESP;

    @JsonProperty("DATARAW")
    private List<AccountResponse> DATARAW;
}
