package com.beta.replyservice.service;

import com.beta.replyservice.model.ReplyMessage;
import org.springframework.stereotype.Component;

@Component
public class ReplyService {

    private static final String EMPTY_MESSAGE = "Message is empty";

    public ReplyMessage getDefaultResponse() {
        return new ReplyMessage(EMPTY_MESSAGE);
    }

    public ReplyMessage getResponse(String message) {
        return new ReplyMessage(message);
    }
}
