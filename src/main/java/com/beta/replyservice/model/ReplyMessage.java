package com.beta.replyservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyMessage {

	@JsonProperty("data")
	private final String message;
}
