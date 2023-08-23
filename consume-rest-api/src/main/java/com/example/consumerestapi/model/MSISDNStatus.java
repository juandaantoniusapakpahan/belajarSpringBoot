package com.example.consumerestapi.model;


import lombok.Data;

@Data
public class MSISDNStatus {
//    "MSISDNStatus": {
//        "serviceId": "{{jsonPath request.body '$.body.serviceId'}}",
//                "status": "Active",
//                "subStatus": "Registered",
//                "subscriberType": "Prepaid",
//                "statusReason": ""
//    }
    private String serviceId;
    private String status;
    private String subStatus;
    private String subscriberType;
    private String statusReason;

}
