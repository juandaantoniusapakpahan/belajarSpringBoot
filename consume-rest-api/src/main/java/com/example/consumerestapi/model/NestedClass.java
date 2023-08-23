package com.example.consumerestapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NestedClass {

    @Data
    private class Body{
        private String transactionId;
        private String channel;
        private String userId;
        private Timestamp timestamp;
        private String intRef;
        private String serviceId;
    }
}
