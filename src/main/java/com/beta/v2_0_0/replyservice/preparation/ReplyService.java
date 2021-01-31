package com.beta.v2_0_0.replyservice.preparation;

import com.beta.replyservice.model.ReplyMessage;
import org.springframework.http.ResponseEntity;

/**
 * Interface to obtain derived result from reply service
 */
public interface ReplyService {
    ResponseEntity<ReplyMessage> getDerivedResponse(String message);
}
