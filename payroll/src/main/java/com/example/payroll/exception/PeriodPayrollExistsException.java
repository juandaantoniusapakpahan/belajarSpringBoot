package com.example.payroll.exception;

public class PeriodPayrollExistsException extends RuntimeException{
    private String message;

    public PeriodPayrollExistsException(String message, Throwable cause){
        super(message, cause);
        this.message = message;
    }

    public PeriodPayrollExistsException(String message){
        super(message);
        this.message = message;
    }
    public PeriodPayrollExistsException(Throwable cause){
        super(cause);
    }
}
