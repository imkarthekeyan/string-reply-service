package com.beta.v2_0_0.replyservice.preparation;

import com.beta.TestDataProvider;
import com.beta.replyservice.model.ReplyMessage;
import jdk.internal.joptsimple.internal.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ReplyServiceAdapterTest extends TestDataProvider {

    @InjectMocks
    private ReplyServiceAdapter replyServiceAdapter;

    @Test
    public void testShouldReturnBadResponseEntityWhenEmptyStringIsPassed() {
        //Given & When
        ResponseEntity<ReplyMessage> responseEntity = replyServiceAdapter.getDerivedResponse(Strings.EMPTY);

        //Then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testShouldReturnBadResponseEntityWhenMessagePassedWithoutDelimiter() {
        //Given & When
        ResponseEntity<ReplyMessage> responseEntity = replyServiceAdapter.getDerivedResponse("11Hello");

        //Then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testShouldReturnBadResponseEntityWhenMessagePassedWrongDelimiter() {
        //Given & When
        ResponseEntity<ReplyMessage> responseEntity = replyServiceAdapter.getDerivedResponse("11#Hello");

        //Then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testShouldReturnBadResponseEntityWhenMessagePartEitherIsEmpty() {
        //Given & When
        ResponseEntity<ReplyMessage> responseEntity = replyServiceAdapter.getDerivedResponse("-Hello");

        //Then
        assertNotNull(responseEntity);
        assertEquals(LENGTH_MISMATCH_ERROR_MESSAGE, Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
