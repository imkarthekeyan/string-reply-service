package com.beta;

public class TestDataProvider {

    // Reply Routes
    public static final String DEFAULT_REPLY_ENDPOINT = "/reply";
    public static final String REPLY_ENDPOINT_VERSION_ONE = "/reply/{message}";
    public static final String REPLY_ENDPOINT_VERSION_TWO = "/v2/reply/{message}";

    public static final String RULE_ONE_CODE = "1";
    public static final String RULE_ONE_NAME = "Reverse";
    public static final String RULE_TWO_CODE = "2";
    public static final String RULE_TWO_NAME = "Encode";
    public static final String RULE_CODE_NAME_EMPTY_NULL_ERROR_MESSAGE = "Error: Number or Rule is null or empty.";
    public static final String RULE_INVALID = "7";
    public static final String RULE_INVALID_ERROR_MESSAGE = "Error: Number(7) Not Supported For Rule Generation!";
    public static final String EMPTY_MESSAGE = "Message is empty";
    public static final String REVERSE_TASK_NAME = "Reverse";
    public static final String HELLO_ACTUAL_MESSAGE = "Hello";
    public static final String HELLO_REVERSED = "olleH";
    public static final String ENCODE_TASK_NAME = "Encode";
    public static final String HELLO_MD5_HASH = "8b1a9953c4611296a827abf8c47804d7";
    public static final String LENGTH_MISMATCH_ERROR_MESSAGE = "Error: Doesn't contain both rule and string.";
}
