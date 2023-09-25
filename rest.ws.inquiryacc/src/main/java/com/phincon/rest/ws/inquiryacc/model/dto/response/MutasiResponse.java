package com.phincon.rest.ws.inquiryacc.model.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MutasiResponse {
    @JsonProperty("TRXDATE")
    private String TRXDATE;

    @JsonProperty("PSTGDATE")
    private String PSTGDATE;

    @JsonProperty("TRXCODE")
    private String TRXCODE;

    @JsonProperty("TRXCODEDSC")
    private String TRXCODEDSC;

    @JsonProperty("DESCRIPTION")
    private String DESCRIPTION;

    @JsonProperty("CURR")
    private String CURR;

    @JsonProperty("TRXAMOUNT")
    private String TRXAMOUNT;

    @JsonProperty("LCLCCY")
    private String LCLCCY;

    @JsonProperty("LCLEQUIVAL")
    private String LCLEQUIVAL;

    @JsonProperty("DBCR")
    private String DBCR;

    @JsonProperty("BUYEXCH")
    private String BUYEXCH;

    @JsonProperty("SELLEXCH")
    private String SELLEXCH;

    @JsonProperty("REFERENSI")
    private String REFERENSI;

    @JsonProperty("CHKBGNBR")
    private String CHKBGNBR;

    @JsonProperty("RUNBAL")
    private String RUNBAL;

    @JsonProperty("EFEKTIF")
    private String EFEKTIF;
}
