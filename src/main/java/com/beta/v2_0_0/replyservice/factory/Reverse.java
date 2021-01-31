package com.beta.v2_0_0.replyservice.factory;

import com.beta.v2_0_0.replyservice.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Execute the provided message by reversing using required algorithm
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reverse implements Rule {

    private static final Logger LOGGER = Logger.getLogger(Reverse.class.getName());

    private static class Holder {
        private static final Reverse INSTANCE = new Reverse();
    }

    public static Reverse getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(String message) {
        LOGGER.log(Level.INFO,"Started: Reverse Task! & Message: " + message);
        return StringUtil.reverseUsingSwap(message);
    }

    @Override
    public String getTask() {
        LOGGER.log(Level.INFO,"Assigned Task: " + Reverse.class.getSimpleName());
        return Reverse.class.getSimpleName();
    }
}
