package com.example.consumerestapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UssdBroadcastResponse {
//    {
//        "pushReferenceId": "230108112021123458",
//            "errCode": "0"
//    }
    private String pushReferenceId;
    private String errCode;
}
