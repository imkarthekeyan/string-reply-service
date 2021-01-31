package com.beta.v2_0_0.replyservice.advice;

import com.beta.replyservice.model.ReplyMessage;
import com.beta.v2_0_0.replyservice.exeception.UnsupportedNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UnsupportedNumberException.class)
    public final ResponseEntity<?> handleException(UnsupportedNumberException exception) {
        return getExceptionResponseEntity(exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<?> handleException(IllegalArgumentException exception) {
        return getExceptionResponseEntity(exception);
    }

    private ResponseEntity<ReplyMessage> getExceptionResponseEntity(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ReplyMessage(exception.getMessage()));
    }
}
