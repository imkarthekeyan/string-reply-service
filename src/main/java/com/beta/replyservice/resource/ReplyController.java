package com.beta.replyservice.resource;

import com.beta.replyservice.model.ReplyMessage;
import com.beta.replyservice.service.ReplyService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Reply controller supports all the versions of reply service
 */
@Controller
@AllArgsConstructor
public class ReplyController {

	private static final String ALLOWED_REGEX = "^[a-z0-9-]*";

	private final ReplyService replyService;
	private final com.beta.v2_0_0.replyservice.preparation.ReplyService replyV2Service;

	@GetMapping("/reply")
	public @ResponseBody ReplyMessage replying() {
		return replyService.getDefaultResponse();
	}

	@GetMapping("/reply/{message}")
	public @ResponseBody ReplyMessage replying(@PathVariable String message) {
		return replyService.getResponse(message);
	}

	@GetMapping("/v2/reply/{message:" + ALLOWED_REGEX + "}")
	public @ResponseBody ResponseEntity<ReplyMessage> replyingV2(@PathVariable String message) {
		return replyV2Service.getDerivedResponse(message);
	}
}
