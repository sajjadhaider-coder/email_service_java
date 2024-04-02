package com.email.CinqdEmail.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.*;


@Embeddable
public class EmailLogId implements Serializable {
	
	private Integer messageId;
	private Timestamp deliveryAttempt;
	public EmailLogId(Integer messageId, Timestamp deliveryAttempt) {
		super();
		this.messageId = messageId;
		this.deliveryAttempt = deliveryAttempt;
	}
	public EmailLogId() {
		// TODO Auto-generated constructor stub
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Timestamp getDeliveryAttempt() {
		return deliveryAttempt;
	}
	public void setDeliveryAttempt(Timestamp deliveryAttempt) {
		this.deliveryAttempt = deliveryAttempt;
	}
	
	
}
