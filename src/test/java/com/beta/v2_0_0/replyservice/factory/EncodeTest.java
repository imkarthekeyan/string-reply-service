package com.beta.v2_0_0.replyservice.factory;

import com.beta.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class EncodeTest extends TestDataProvider {

    @InjectMocks
    private Encode encode;

    @Test()
    public void testShouldReturnMD5HashWhenActualMessageIsPassed() {
        //Given & When
        String hashResponse = encode.execute(HELLO_ACTUAL_MESSAGE);

        //Then
        assertNotNull(hashResponse);
        assertEquals(HELLO_MD5_HASH, hashResponse);
    }

    @Test()
    public void testShouldReturnTaskNameWhenGetTaskIsMethod() {
        //Given & When
        String taskName = encode.getTask();

        //Then
        assertNotNull(taskName);
        assertEquals(ENCODE_TASK_NAME, taskName);
    }
}
