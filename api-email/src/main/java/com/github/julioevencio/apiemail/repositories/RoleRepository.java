package com.github.julioevencio.apiemail.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.julioevencio.apiemail.entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

	Optional<RoleEntity> findByName(String name);

}