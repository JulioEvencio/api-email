package com.github.julioevencio.apiemail.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.julioevencio.apiemail.entities.EmailEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, UUID> {

}
