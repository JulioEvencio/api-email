package com.github.julioevencio.apiemail.services;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.github.julioevencio.apiemail.dto.email.EmailMapperDTO;
import com.github.julioevencio.apiemail.dto.email.EmailRequestDTO;
import com.github.julioevencio.apiemail.dto.email.EmailResponseDTO;
import com.github.julioevencio.apiemail.entities.EmailEntity;
import com.github.julioevencio.apiemail.entities.RecipientEntity;
import com.github.julioevencio.apiemail.entities.UserEntity;
import com.github.julioevencio.apiemail.repositories.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;
	private final UserService userService;
	private final EmailRepository emailRepository;

	public EmailServiceImpl(JavaMailSender javaMailSender, UserService userService, EmailRepository emailRepository) {
		this.javaMailSender = javaMailSender;
		this.userService = userService;
		this.emailRepository = emailRepository;
	}

	@Override
	@Transactional
	public EmailResponseDTO sendEmail(EmailRequestDTO dto) {
		EmailEntity emailEntity = EmailMapperDTO.fromDTO(dto);
		UserEntity user = userService.getLoggedUser();
		List<RecipientEntity> recipientEntities = dto.getRecipients().stream().map(recipient -> new RecipientEntity(null, recipient, emailEntity)).toList();

		emailEntity.setUser(user);
		emailEntity.setRecipients(recipientEntities);
		
		Thread thread = new Thread(() -> {
			recipientEntities.stream().forEach(recipient -> {
				try {
					MimeMessage mimeMessage = javaMailSender.createMimeMessage();
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					
					mimeMessageHelper.setTo(recipient.getRecipient());
					mimeMessageHelper.setSubject(dto.getSubject());
					mimeMessageHelper.setText(dto.getContent(), true);
					
					javaMailSender.send(mimeMessage);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			});
		});
		thread.start();

		return EmailMapperDTO.fromEntity(emailRepository.save(emailEntity));
	}

}
