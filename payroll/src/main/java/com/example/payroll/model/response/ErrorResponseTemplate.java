package com.example.payroll.model.response;




public class ErrorResponseTemplate {

    private Integer statusCode;
    private String message;


    public Integer getStatus() {
        return statusCode;
    }

    public void setStatus(int statusCode) {
        this.statusCode = statusCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponseTemplate(
                                 final Integer statusCode,
                                 final String message
                                ){
        this.statusCode = statusCode;
        this.message = message;

    }
    public ErrorResponseTemplate(){}
}
