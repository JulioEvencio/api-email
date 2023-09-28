package com.github.julioevencio.apiemail.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ApiEmailMessageError implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final List<String> errors;
	private final LocalDateTime timestamp;

	public ApiEmailMessageError(String message, List<String> errors) {
		this.message = message;
		this.errors = errors;
		this.timestamp = LocalDateTime.now();
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
