package com.beta.v2_0_0.replyservice.factory;

import com.beta.v2_0_0.replyservice.utils.Hash;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Execute the provided message by encoding it using MD5 Hash Algorithm
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Encode implements Rule {

    private static final Logger LOGGER = Logger.getLogger(Encode.class.getName());

    private static volatile Encode instance = null;

    public static Encode getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (Encode.class) {
                if (Objects.isNull(instance)) {
                    instance = new Encode();
                }
            }
        }
        return instance;
    }

    @Override
    public String execute(String message) {
        LOGGER.log(Level.INFO,"Started: MD5 Encode Task! & Message: " + message);
        return Hash.md5(message);
    }

    @Override
    public String getTask() {
        LOGGER.log(Level.INFO,"Assigned Task: " + Encode.class.getSimpleName());
        return Encode.class.getSimpleName();
    }
}
