package com.example.consumerestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SiebelMessage {
//    "SiebelMessage": {
//        "IntObjectFormat": "Siebel Hierarchical",
//                "MessageId": "",
//                "IntObjectName": "MSISDNStatus",
//                "MessageType": "Integration Object",
//                "MSISDNStatus": {

    @JsonProperty(value = "IntObjectFormat")
    private String intObjectFormat;
    @JsonProperty(value = "MessageId")
    private String messageId;
    @JsonProperty(value="IntObjectName")
    private String intObjectName;
    @JsonProperty(value = "MessageType")
    private String messageType;
    @JsonProperty(value = "MSISDNStatus")
    private MSISDNStatus msisdnStatus;

}
