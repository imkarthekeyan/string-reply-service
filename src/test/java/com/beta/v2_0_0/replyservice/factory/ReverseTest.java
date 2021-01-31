package com.beta.v2_0_0.replyservice.factory;

import com.beta.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ReverseTest extends TestDataProvider {

    @InjectMocks
    private Reverse reverse;

    @Test()
    public void testShouldReturnReverseMessageWhenActualMessageIsPassed() {
        //Given & When
        String responseMessage = reverse.execute(HELLO_ACTUAL_MESSAGE);

        //Then
        assertNotNull(responseMessage);
        assertEquals(HELLO_REVERSED, responseMessage);
    }

    @Test()
    public void testShouldReturnTaskNameWhenGetTaskIsMethod() {
        //Given & When
        String taskName = reverse.getTask();

        //Then
        assertNotNull(taskName);
        assertEquals(REVERSE_TASK_NAME, taskName);
    }
}
