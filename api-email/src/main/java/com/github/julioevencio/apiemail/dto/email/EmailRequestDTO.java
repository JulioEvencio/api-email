package com.github.julioevencio.apiemail.dto.email;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	private final List<@Email(message = "Recipient's e-mail is invalid") @Size(max = 100, min = 1, message = "The Recipient's e-mail must be between 1 and 100 characters long") String> recipients;

	@NotBlank(message = "Invalid subject")
	@Size(max = 100, min = 1, message = "The username must be between 1 and 100 characters long")
	private final String subject;

	@NotBlank(message = "Invalid content")
	private final String content;

	public EmailRequestDTO(List<String> recipients, String subject, String content) {
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
