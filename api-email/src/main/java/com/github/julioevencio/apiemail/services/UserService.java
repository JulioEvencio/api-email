package com.github.julioevencio.apiemail.services;

import java.util.List;

import com.github.julioevencio.apiemail.dto.security.LoginRequestDTO;
import com.github.julioevencio.apiemail.dto.security.TokenResponseDTO;
import com.github.julioevencio.apiemail.dto.user.UserRequestDTO;
import com.github.julioevencio.apiemail.dto.user.UserResponseDTO;

public interface UserService {

	UserResponseDTO register(UserRequestDTO dto);

	TokenResponseDTO login(LoginRequestDTO dto);

	UserResponseDTO me();

	List<UserResponseDTO> findAll();

}
