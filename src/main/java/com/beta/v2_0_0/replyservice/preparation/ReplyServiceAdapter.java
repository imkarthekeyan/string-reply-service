package com.beta.v2_0_0.replyservice.preparation;

import com.beta.replyservice.model.ReplyMessage;
import com.beta.v2_0_0.replyservice.factory.Rule;
import com.beta.v2_0_0.replyservice.factory.RuleFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Reply implementation for the provided message from all the clients
 */
@Component("replyServiceAdapter.v2_0_0")
public class ReplyServiceAdapter implements ReplyService {

    private static final Logger LOGGER = Logger.getLogger(ReplyServiceAdapter.class.getName());

    private static final String DASH_DELIMITER = "-";

    /**
     * Returns a ReplyMessage object based on the provided message
     */
    @Override
    public ResponseEntity<ReplyMessage> getDerivedResponse(String message) {

        LOGGER.info("Actual Message: " + message);
        if (StringUtils.isEmpty(message) || !message.contains(DASH_DELIMITER)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ReplyMessage("Error: Actual message ("+ message +") is null or empty or invalid delimiter."));
        }

        String[] parts = message.split(DASH_DELIMITER);
        if (Objects.isNull(parts) || Strings.isEmpty(parts[0]) || parts.length == 1 || Strings.isEmpty(parts[1])) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ReplyMessage("Error: Doesn't contain both rule and string."));
        }
        List<Rule> ruleList = getAssignedRuleList(parts[0]);
        return ResponseEntity.ok(getFinalCombinedResponse(ruleList, parts[1]));
    }

    /**
     * Returns a list of all the assigned Rule object
     */
    private List<Rule> getAssignedRuleList(String numbers) {
        List<Rule> ruleList = new LinkedList<>();
        numbers.chars().forEachOrdered(c -> {
            Rule currentRule = RuleFactory.getRule(String.valueOf((char)c));
            ruleList.add(currentRule);
        });
        return ruleList;
    }

    /**
     * Returns a ReplyMessage object based on the provided rules and letters
     */
    private ReplyMessage getFinalCombinedResponse(List<Rule> ruleList, String letters) {
        final String[] response = {letters};
        Consumer<Rule> consumer = rule -> {
            rule.getTask();
            response[0] = rule.execute(response[0]);
        };
        ruleList.forEach(consumer);
        return new ReplyMessage(response[0]);
    }
}
