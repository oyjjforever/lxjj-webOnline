package com.lxjj.wo.common.entity;

public class BusinessException extends Exception {
    private StatusCode statusCode;
    private String message;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(StatusCode statusCode,String message) {
        super(message);
        this.statusCode =statusCode;

    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }}
