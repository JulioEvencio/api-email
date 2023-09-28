package com.github.julioevencio.apiemail.services;

import java.util.List;

import com.github.julioevencio.apiemail.dto.security.TokenResponseDTO;

public interface TokenJwtService {

	TokenResponseDTO createAccessToken(String username, List<String> roles);

	boolean validateToken(String token);

	String getUsername(String token);

}