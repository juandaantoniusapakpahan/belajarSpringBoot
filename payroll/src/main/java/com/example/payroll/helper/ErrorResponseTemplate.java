package com.example.payroll.helper;




public class ErrorResponseTemplate {

    private int status;
    private String error;
    private String message;



    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ErrorResponseTemplate(
                                 int status,
                                 String error,
                                 String message
                                ){
        this.status = status;
        this.message = message;
        this.error = error;

    }

    public ErrorResponseTemplate(){}
}