package com.github.julioevencio.apiemail.dto.email;

import java.util.List;

import com.github.julioevencio.apiemail.entities.EmailEntity;
import com.github.julioevencio.apiemail.entities.RecipientEntity;

public class EmailMapperDTO {

	public static EmailEntity fromDTO(EmailRequestDTO dto) {
		List<RecipientEntity> recipientEntities = dto.getRecipients().stream().map(recipient -> new RecipientEntity(null, recipient, null)).toList();

		return new EmailEntity(null, null, recipientEntities, dto.getSubject(), dto.getContent());
	}

	public static EmailResponseDTO fromEntity(EmailEntity entity) {
		List<String> recipients = entity.getRecipients().stream().map(recipient -> recipient.getRecipient()).toList();

		return new EmailResponseDTO(recipients, entity.getSubject(), entity.getContent());
	}

}
