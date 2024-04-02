package com.email.CinqdEmail.Entity;

import java.sql.Timestamp;

import jakarta.persistence.*;


@Entity
public class CinqdEmailLogStaging implements java.io.Serializable {

	@EmbeddedId
	EmailLogId id; 
	private String errorMessage;
	private String status;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EmailLogId getId() {
		return id;
	}
	public void setId(EmailLogId id) {
		this.id = id;
	}
	
}
