package com.github.julioevencio.apiemail.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.julioevencio.apiemail.dto.security.LoginRequestDTO;
import com.github.julioevencio.apiemail.dto.security.TokenResponseDTO;
import com.github.julioevencio.apiemail.dto.user.UserMapperDTO;
import com.github.julioevencio.apiemail.dto.user.UserRequestDTO;
import com.github.julioevencio.apiemail.dto.user.UserResponseDTO;
import com.github.julioevencio.apiemail.entities.RoleEntity;
import com.github.julioevencio.apiemail.entities.UserEntity;
import com.github.julioevencio.apiemail.exceptions.ApiEmailLoginException;
import com.github.julioevencio.apiemail.exceptions.ApiEmailResourceNotFoundException;
import com.github.julioevencio.apiemail.exceptions.ApiEmailSQLException;
import com.github.julioevencio.apiemail.repositories.RoleRepository;
import com.github.julioevencio.apiemail.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final TokenJwtService tokenJwtService;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, TokenJwtService tokenJwtService, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.tokenJwtService = tokenJwtService;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	@Transactional
	public UserResponseDTO register(UserRequestDTO dto) {
		if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
			throw new ApiEmailSQLException("Username already exists");
		}

		if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new ApiEmailSQLException("E-mail already exists");
		}

		UserEntity user = UserMapperDTO.fromDTO(dto);

		user.setEnabled(true);
		user.setCredentialsNonExpired(true);
		user.setAccountNonLocked(true);
		user.setAccountNonExpired(true);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		List<RoleEntity> roles = new ArrayList<>();
		roles.add(roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException()));

		user.setRoles(roles);

		return UserMapperDTO.fromEntity(userRepository.save(user));
	}

	@Override
	public TokenResponseDTO login(LoginRequestDTO dto) {
		UserEntity user = userRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new ApiEmailLoginException("Username not found"));

		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			throw new ApiEmailLoginException("Invalid password");
		}

		List<String> roles = user.getRoles().stream().map(role -> role.toString()).toList();

		return tokenJwtService.createAccessToken(user.getUsername(), roles);
	}

	@Override
	public UserResponseDTO me() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new ApiEmailResourceNotFoundException("User not found"));

		return UserMapperDTO.fromEntity(user);
	}

	@Override
	public List<UserResponseDTO> findAll() {
		return userRepository.findAll().stream().map(UserMapperDTO::fromEntity).toList();
	}

}
