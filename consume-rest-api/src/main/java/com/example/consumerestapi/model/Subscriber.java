package com.example.consumerestapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.Timestamp;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Subscriber {

//    {
//        "body": {
//        "transactionId": "23082200001",
//                "channel": "k4",
//                "userId": "Testuser1",
//                "timestamp": "20191004 12:33:33:334",
//                "intRef": "SubscriptionStatusQuery",
//                "serviceId": "6281188885555"
//    }
//    }
    private String transactionId;
    private String channel;
    private String userId;
    private String timestamp;
    private String intRef;
    private String serviceId;

}
