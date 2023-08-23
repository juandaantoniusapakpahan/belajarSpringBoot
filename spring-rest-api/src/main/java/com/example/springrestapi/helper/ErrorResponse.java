package com.example.springrestapi.helper;

import org.hibernate.annotations.CurrentTimestamp;

import java.util.List;

public class ErrorResponse {

    private CurrentTimestamp timestamp;
    private String status;
    private List<String> message;
}
