package com.github.julioevencio.apiemail.dto.email;

import java.io.Serializable;
import java.util.List;

public class EmailResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final List<String> recipients;
	private final String subject;
	private final String content;

	public EmailResponseDTO(List<String> recipients, String subject, String content) {
		this.recipients = recipients;
		this.subject = subject;
		this.content = content;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

}
