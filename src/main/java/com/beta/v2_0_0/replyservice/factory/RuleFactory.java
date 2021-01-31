package com.beta.v2_0_0.replyservice.factory;

import com.beta.v2_0_0.replyservice.exeception.UnsupportedNumberException;
import com.beta.v2_0_0.replyservice.utils.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rule factory creates required object for all the clients that needs any type of objects
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RuleFactory {

    private static final Logger LOGGER = Logger.getLogger(RuleFactory.class.getName());

    private static final Map<String, Rule> RULE_MAP = new HashMap<>();

    /**
     * Returns a Rule object type based on the clients input
     */
    public static Rule getRule(String number) {
        Rule rule;
        if (Objects.nonNull(RULE_MAP.get(number))) {
            rule = RULE_MAP.get(number);
        } else {
            switch (number) {
                case Constants.RULE_ONE_CODE:
                    rule = Reverse.getInstance();
                    LOGGER.log(Level.INFO,"Rule Created! Name: " + rule.getTask());
                    break;
                case Constants.RULE_TWO_CODE:
                    rule = Encode.getInstance();
                    LOGGER.log(Level.INFO,"Rule Created! Name: " + rule.getTask());
                    break;
                default:
                    throw new UnsupportedNumberException("Error: Number(" + number + ") Not Supported For Rule Generation!");
            }
            RULE_MAP.put(number, rule);
        }
        return rule;
    }

    /**
     * Assign the rule manually by providing a number and rule
     */
    public static Map<String, Rule> setManualRule(String number, String existingRuleName) {
        if (StringUtils.isEmpty(number) || StringUtils.isEmpty(existingRuleName)) {
            throw new IllegalArgumentException("Error: Number or Rule is null or empty.");
        }
        if (Constants.RULE_ONE_NAME.equalsIgnoreCase(existingRuleName)) {
            RULE_MAP.put(number, Reverse.getInstance());
        } else if (Constants.RULE_TWO_NAME.equalsIgnoreCase(existingRuleName)) {
            RULE_MAP.put(number, Encode.getInstance());
        }
        return RULE_MAP;
    }
}
