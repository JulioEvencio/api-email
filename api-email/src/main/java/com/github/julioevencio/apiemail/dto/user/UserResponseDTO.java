package com.github.julioevencio.apiemail.dto.user;

import java.io.Serializable;
import java.util.UUID;

public class UserResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final UUID id;
	private final String username;
	private final String email;

	public UserResponseDTO(UUID id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

}
