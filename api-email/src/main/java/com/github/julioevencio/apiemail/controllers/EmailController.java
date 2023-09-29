package com.github.julioevencio.apiemail.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioevencio.apiemail.dto.email.EmailRequestDTO;
import com.github.julioevencio.apiemail.dto.email.EmailResponseDTO;
import com.github.julioevencio.apiemail.exceptions.ApiEmailMessageError;
import com.github.julioevencio.apiemail.services.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/emails")
@Tag(name = "Emails", description = "Endpoints for emails")
public class EmailController {

	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping(path = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Send an email",
			description = "Send an email",
			tags = {"Emails"},
			responses = {
					@ApiResponse(
							responseCode = "201",
							description = "Send an email",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = EmailResponseDTO.class)
							)
					),
					@ApiResponse(
							responseCode = "400",
							description = "Bad request",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiEmailMessageError.class)
							)
					),
					@ApiResponse(
							responseCode = "401",
							description = "Unauthorized",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiEmailMessageError.class)
							)
					)
			}
	)
	public ResponseEntity<EmailResponseDTO> sendEmail(@RequestBody @Valid EmailRequestDTO dto) {
		EmailResponseDTO response = emailService.sendEmail(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
