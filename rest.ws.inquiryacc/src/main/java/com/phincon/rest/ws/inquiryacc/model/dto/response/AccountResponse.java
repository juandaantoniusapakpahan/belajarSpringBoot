package com.phincon.rest.ws.inquiryacc.model.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    @JsonProperty("ACCOUNTNBR")
    private String ACCOUNTNBR;

    @JsonProperty("PRODUCTNAME")
    private String PRODUCTNAME;

    @JsonProperty("PRODUCTTP")
    private String PRODUCTTP;

    @JsonProperty("CURR")
    private String CURR;

    @JsonProperty("PRIMARY")
    private String PRIMARY;

    @JsonProperty("ACCTSTAT")
    private String ACCTSTAT;

    @JsonProperty("ATMCARD")
    private String ATMCARD;
}
