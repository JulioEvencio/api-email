package com.github.julioevencio.apiemail.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.julioevencio.apiemail.entities.RecipientEntity;

@Repository
public interface RecipientRepository extends JpaRepository<RecipientEntity, UUID> {

}
