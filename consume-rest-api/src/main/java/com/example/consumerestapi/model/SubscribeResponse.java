package com.example.consumerestapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscribeResponse {

    private String responseCode;
    private String responseMessage;
    private String transactionId;
    @JsonProperty(value = "SiebelMessage")
    private SiebelMessage siebelMessage;
}
