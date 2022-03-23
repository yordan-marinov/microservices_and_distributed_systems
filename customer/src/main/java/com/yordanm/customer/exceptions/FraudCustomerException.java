package com.yordanm.customer.exceptions;

public class FraudCustomerException extends RuntimeException{
    public FraudCustomerException(String msg){
        super(msg);
    }
}
