package org.checkr.reports.exception;

public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(String message){
        super(message);
    }
}
