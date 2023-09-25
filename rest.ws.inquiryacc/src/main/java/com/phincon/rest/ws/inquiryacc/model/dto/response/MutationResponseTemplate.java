package com.phincon.rest.ws.inquiryacc.model.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MutationResponseTemplate {
    @JsonProperty("RESP")
    private String RESP;

    @JsonProperty("MOREDTA")
    private String MOREDTA;

    @JsonProperty("LASTINDEX")
    private String LASTINDEX;

    @JsonProperty("MUTASI")
    private List<MutasiResponse> MUTASI;
}
