package com.github.julioevencio.apiemail.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_recipient")
public class RecipientEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, length = 100)
	private String recipient;

	@ManyToOne
	private EmailEntity email;

	public RecipientEntity() {
	}

	public RecipientEntity(UUID id, String recipient, EmailEntity email) {
		this.id = id;
		this.recipient = recipient;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public EmailEntity getEmail() {
		return email;
	}

	public void setEmail(EmailEntity email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, recipient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipientEntity other = (RecipientEntity) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(recipient, other.recipient);
	}

}
