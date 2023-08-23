package com.example.payroll.helper;




public class ErrorResponseTemplate {

    private int status;
    private String error;
    private String message;
    private String path;


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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ErrorResponseTemplate(
                                 int status,
                                 String error,
                                 String message,
                                 String path){
        this.status = status;
        this.message = message;
        this.error = error;
        this.path = path;
    }

    public ErrorResponseTemplate(){}
}
