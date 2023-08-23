package com.example.consumerestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UssdBroadcastRequest {
//    {
//        "pushReferenceId": "230108112021123458",
//            "msisdn": "6281310134399",
//            "mms": "1",
//            "operationType": "SP",
//            "ussdContent": "This is USSD message",
//            "ussdShortCode": "",
//            "CallbackUrl": "https://10.59.69.67:5000/callback/digihub/ussdapi"
//    }

    private String pushReferenceId;
    private String msisdn;
    private String mms;
    private String operationType;
    private String ussdShortCode;
    @JsonProperty(value = "CallbackUrl")
    private String callbackUrl;
}
