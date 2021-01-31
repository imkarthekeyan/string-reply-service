package com.beta.v2_0_0.replyservice.exeception;

/**
 * Custom Unsupported Number exception for input type
 */
public class UnsupportedNumberException extends RuntimeException {

    public UnsupportedNumberException (String message) {
        super(message);
    }
}
