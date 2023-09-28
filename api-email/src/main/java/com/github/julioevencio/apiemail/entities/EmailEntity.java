package com.github.julioevencio.apiemail.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_email")
public class EmailEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private UserEntity user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "email")
	private List<RecipientEntity> recipients;

	@Column(nullable = false, length = 100)
	private String subject;

	@Column(nullable = false)
	private String content;

	public EmailEntity() {
	}

	public EmailEntity(UUID id, UserEntity user, List<RecipientEntity> recipients, String subject, String content) {
		this.id = id;
		this.user = user;
		this.recipients = recipients;
		this.subject = subject;
		this.content = content;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<RecipientEntity> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<RecipientEntity> recipients) {
		this.recipients = recipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, id, recipients, subject, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailEntity other = (EmailEntity) obj;
		return Objects.equals(content, other.content) && Objects.equals(id, other.id)
				&& Objects.equals(recipients, other.recipients) && Objects.equals(subject, other.subject)
				&& Objects.equals(user, other.user);
	}	
	
}
