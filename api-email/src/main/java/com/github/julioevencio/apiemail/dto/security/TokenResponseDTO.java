package com.github.julioevencio.apiemail.dto.security;

import java.io.Serializable;
import java.util.Date;

public class TokenResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String username;
	private final Boolean authenticated;
	private final Date created;
	private final Date expiration;
	private final String accessToken;

	public TokenResponseDTO(String username, Boolean authenticated, Date created, Date expiration, String accessToken) {
		this.username = username;
		this.authenticated = authenticated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
	}

	public String getUsername() {
		return username;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public Date getCreated() {
		return created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

}
