package com.beta.v2_0_0.replyservice.factory;

/**
 * Interface to apply the assigned rule for execution
 */
public interface Rule {
    String execute(String message);
    String getTask();
}
