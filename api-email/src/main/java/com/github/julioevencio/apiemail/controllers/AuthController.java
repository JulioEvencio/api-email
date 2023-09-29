package com.github.julioevencio.apiemail.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioevencio.apiemail.dto.security.LoginRequestDTO;
import com.github.julioevencio.apiemail.dto.security.TokenResponseDTO;
import com.github.julioevencio.apiemail.dto.user.UserRequestDTO;
import com.github.julioevencio.apiemail.dto.user.UserResponseDTO;
import com.github.julioevencio.apiemail.exceptions.ApiEmailMessageError;
import com.github.julioevencio.apiemail.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth")
@Tag(name = "Auth", description = "Endpoints for authentication")
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Register a user",
			description = "Register a user",
			tags = {"Auth"},
			responses = {
					@ApiResponse(
							responseCode = "201",
							description = "User created",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = UserResponseDTO.class)
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
							responseCode = "422",
							description = "Unprocessable entity",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiEmailMessageError.class)
							)
					)
			}
	)
	public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO dto) {
		UserResponseDTO response = userService.register(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Generate jwt token",
			description = "Generate jwt token",
			tags = {"Auth"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Generate jwt token",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = UserResponseDTO.class)
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
							responseCode = "422",
							description = "Unprocessable entity",
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
	public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
		TokenResponseDTO response = userService.login(dto);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
