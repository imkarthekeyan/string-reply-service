package com.beta.v2_0_0.replyservice.factory;

import com.beta.TestDataProvider;
import com.beta.v2_0_0.replyservice.exeception.UnsupportedNumberException;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class RuleFactoryTest extends TestDataProvider {

    // @Test
    public void testShouldReturnReverseRuleWhenFactoryRuleOneIsPassed() {
        //Given & When
        Rule obtainedRule = RuleFactory.getRule(RULE_ONE_CODE);

        //Then
        assertNotNull(obtainedRule);
    }

    @Test
    public void testShouldReturnReverseRuleWhenFactoryRuleOneIsPassedTwice() {
        //Given & When
        Rule obtainedRuleOneFirst = RuleFactory.getRule(RULE_ONE_CODE);
        Rule obtainedRuleOneSecond = RuleFactory.getRule(RULE_ONE_CODE);

        //Then
        assertNotNull(obtainedRuleOneFirst);
        assertNotNull(obtainedRuleOneSecond);
    }

    @Test
    public void testShouldReturnEncodeRuleWhenFactoryRuleTwoIsPassed() {
        //Given & When
        Rule obtainedRule = RuleFactory.getRule(RULE_TWO_CODE);

        //Then
        assertNotNull(obtainedRule);
        assertEquals(ENCODE_TASK_NAME, obtainedRule.getTask());
    }

    @Test
    public void testShouldThrowUnsupportedNumberExceptionWhenInvalidDigitIsPassed() {
        //Given & When
        UnsupportedNumberException thrown = assertThrows(
                UnsupportedNumberException.class,
                () -> RuleFactory.getRule(RULE_INVALID));

        //Then
        assertNotNull(thrown);
        assertEquals(RULE_INVALID_ERROR_MESSAGE, thrown.getMessage());
    }

    @Test
    public void testShouldReturnEncodeRuleWhenSetManualRuleOneIsPassed() {
        //Given & When
        Map<String, Rule> obtainedRuleMap = RuleFactory.setManualRule(RULE_ONE_CODE, RULE_TWO_NAME);

        //Then
        assertNotNull(obtainedRuleMap);
        Rule derivedRule = obtainedRuleMap.get(RULE_ONE_CODE);
        assertEquals(ENCODE_TASK_NAME, derivedRule.getTask());
    }

    @Test
    public void testShouldReturnReverseRuleWhenSetManualRuleTwoIsPassed() {
        //Given & When
        Map<String, Rule> obtainedRuleMap = RuleFactory.setManualRule(RULE_TWO_CODE, RULE_ONE_NAME);

        //Then
        assertNotNull(obtainedRuleMap);
        assertTrue(obtainedRuleMap.size() > 1);
        Rule derivedRule = obtainedRuleMap.get(RULE_TWO_CODE);
        assertEquals(REVERSE_TASK_NAME, derivedRule.getTask());
    }

    @Test
    public void testShouldThrowIllegalArgumentExceptionWhenSetManualRuleNumberPassedIsEmpty() {
        //Given & When
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> RuleFactory.setManualRule(Strings.EMPTY, RULE_ONE_NAME));

        //Then
        assertNotNull(thrown);
        assertEquals(RULE_CODE_NAME_EMPTY_NULL_ERROR_MESSAGE, thrown.getMessage());
    }

    @Test
    public void testShouldThrowIllegalArgumentExceptionWhenSetManualRuleNumberPassedIsNull() {
        //Given & When
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> RuleFactory.setManualRule(null, RULE_TWO_NAME));

        //Then
        assertNotNull(thrown);
        assertEquals(RULE_CODE_NAME_EMPTY_NULL_ERROR_MESSAGE, thrown.getMessage());
    }

    @Test
    public void testShouldThrowIllegalArgumentExceptionWhenSetManualRuleNamePassedIsEmpty() {
        //Given & When
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> RuleFactory.setManualRule(RULE_ONE_CODE, Strings.EMPTY));

        //Then
        assertNotNull(thrown);
        assertEquals(RULE_CODE_NAME_EMPTY_NULL_ERROR_MESSAGE, thrown.getMessage());
    }

    @Test
    public void testShouldThrowIllegalArgumentExceptionWhenSetManualRuleNamePassedIsNull() {
        //Given & When
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> RuleFactory.setManualRule(RULE_ONE_CODE, null));

        //Then
        assertNotNull(thrown);
        assertEquals(RULE_CODE_NAME_EMPTY_NULL_ERROR_MESSAGE, thrown.getMessage());
    }
}
