package com.beta.replyservice.service;

import com.beta.TestDataProvider;
import com.beta.replyservice.model.ReplyMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ReplyServiceTest extends TestDataProvider {

    @InjectMocks
    private ReplyService replyService;

    @Test()
    public void testShouldReturnReplyMessageWhenDefaultResponseMethodIsInvoked() {
        //Given & When
        ReplyMessage replyMessage = replyService.getDefaultResponse();

        //Then
        assertNotNull(replyMessage);
        assertEquals(EMPTY_MESSAGE, replyMessage.getMessage());
    }

    @Test()
    public void testShouldReturnReplyMessageWhenResponseMethodIsInvoked() {
        //Given
        String message = "Hello World!";

        //When
        ReplyMessage replyMessage = replyService.getResponse(message);

        //Then
        assertNotNull(replyMessage);
        assertEquals(message, replyMessage.getMessage());
    }
}
