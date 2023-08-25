package com.example.payroll.helper;




public class ErrorResponseTemplate {

    private int statusCode;
    private String message;



    public Integer getStatus() {
        return statusCode;
    }

    public void setStatus(int status) {
        this.statusCode = status;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ErrorResponseTemplate(
                                 int statusCode,
                                 String message
                                ){
        this.statusCode = statusCode;
        this.message = message;

    }

    public ErrorResponseTemplate(){}
}
