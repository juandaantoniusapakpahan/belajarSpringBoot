package com.phincon.rest.ws.inquiryacc.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.rest.ws.inquiryacc.model.dto.response.InquiryAccountResponseTemplate;
import com.phincon.rest.ws.inquiryacc.service.AccountService;
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


@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private AccountController accountController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When request GET inquiryAccount should return correct response")
    void whenGetRequestInquiryAccount_thenReturnCorrectResponse() throws Exception{
        String cifnbr = "0001062020";

        ObjectMapper mapper = new ObjectMapper();
        InquiryAccountResponseTemplate inquiryAccountResponse = mapper.readValue(new File("src/main/resources/specification/InquiryAccount_response.json"), InquiryAccountResponseTemplate.class);
        when(accountService.inquiryAccount(cifnbr)).thenReturn(inquiryAccountResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/web/services/INQACCT/INQACCT/{cifnbr}", cifnbr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("COUNT")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("RESP")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasKey("DATARAW")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.DATARAW").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.DATARAW[0]").value(hasKey("ACCOUNTNBR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.DATARAW[0]").value(hasKey("PRODUCTNAME")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.DATARAW[0]").value(hasKey("PRODUCTTP")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.DATARAW[0]").value(hasKey("CURR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.DATARAW[0]").value(hasKey("PRIMARY")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.DATARAW[0]").value(hasKey("ACCTSTAT")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.DATARAW[0]").value(hasKey("ATMCARD")));
    }

}
