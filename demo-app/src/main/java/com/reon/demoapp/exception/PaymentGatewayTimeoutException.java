package com.reon.demoapp.exception;

public class PaymentGatewayTimeoutException extends RuntimeException{
    public PaymentGatewayTimeoutException(String message) {
        super(message);
    }
}
