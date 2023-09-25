package com.phincon.rest.ws.inquiryacc.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.rest.ws.inquiryacc.model.dto.request.CCRequestBody;
import com.phincon.rest.ws.inquiryacc.model.dto.response.CCResponse;
import com.phincon.rest.ws.inquiryacc.service.CreditCartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;

import static org.hamcrest.Matchers.hasKey;
import static org.mockito.Mockito.when;

@WebMvcTest(CreditCartController.class)
public class CreditCartControllerTest {

    @MockBean
    private CreditCartService creditCartService;

    @Autowired
    private CreditCartController creditCartController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when credit cart request then should response 200")
    void whenRequestCartNumber_thenReturnCorrectResponse() throws Exception{
        CCRequestBody rqBody = new CCRequestBody("4377014000235208");

        ObjectMapper mapper = new ObjectMapper();
        CCResponse response = mapper.readValue(new File("src/main/resources/specification/CreditCard_cardnumber_response.json"), CCResponse.class);

        when(creditCartService.creditCard(rqBody)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/web/services/creditcard")
                .header("service", "listCustomerCIFCardSummaryListInput")
                .header("traceID", "ABCDEFHIJKLMNOPQRSTUVWXY7")
                .header("channel", "CC")
                .header("timestamp", "2023-09-19 15:48:57.821")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(rqBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("rsHeader")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader").value(hasKey("rqHeader")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader.rqHeader").value(hasKey("service")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader.rqHeader").value(hasKey("traceID")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader.rqHeader").value(hasKey("channel")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader.rqHeader").value(hasKey("timestamp")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader.rqHeader").value(hasKey("listKey")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader").value(hasKey("timeStamp")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader").value(hasKey("status")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader").value(hasKey("statusDesc")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader").value(hasKey("listCount")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader").value(hasKey("listCount")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsHeader").value(hasKey("listKey")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("rsBody")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardNbr")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardPrd")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardPrdCurr")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardPrdType")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardAplType")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardSts")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardBlk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardInactiveDt")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardCrLimit")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardCrLimit")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rsBody[0]").value(hasKey("cardAcPyDueDt")));




    }
}
