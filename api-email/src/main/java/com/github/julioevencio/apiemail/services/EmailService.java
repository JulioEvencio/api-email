package com.github.julioevencio.apiemail.services;

import com.github.julioevencio.apiemail.dto.email.EmailRequestDTO;
import com.github.julioevencio.apiemail.dto.email.EmailResponseDTO;

public interface EmailService {

	EmailResponseDTO sendEmail(EmailRequestDTO dto);

}
