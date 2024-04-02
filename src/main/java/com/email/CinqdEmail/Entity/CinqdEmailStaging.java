package com.email.CinqdEmail.Entity;

import java.sql.Timestamp;

import jakarta.persistence.*;


@Entity
public class CinqdEmailStaging implements java.io.Serializable {

	@Id
	@Column(name = "messageId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageId;
	private Integer businessId;
	private Timestamp created;
	private String sender;
	private String recipient;
	private String cc;
	private String bcc;
	private String subject;
	
	@Column(length = 65555)
	private String message;
	
	private String status;
	private Integer deliveryAttempts;
	private Timestamp delivered;
	private Integer attributes;
	private Timestamp executeDelivery;
	private Integer configurationId;
	
	public CinqdEmailStaging(){}
	
	public CinqdEmailStaging(Integer messageId, Integer businessId, Timestamp created, String sender, String recipient, String cc,
			String bcc, String subject, String message, String status, Integer deliveryAttempts, Timestamp delivered,
			Integer attributes, Timestamp executeDelivery, Integer configurationId) {
		super();
		this.messageId = messageId;
		this.businessId = businessId;
		this.created = created;
		this.sender = sender;
		this.recipient = recipient;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.message = message;
		this.status = status;
		this.deliveryAttempts = deliveryAttempts;
		this.delivered = delivered;
		this.attributes = attributes;
		this.executeDelivery = executeDelivery;
		this.configurationId = configurationId;
	}
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getDeliveryAttempts() {
		return deliveryAttempts;
	}
	public void setDeliveryAttempts(Integer deliveryAttempts) {
		this.deliveryAttempts = deliveryAttempts;
	}
	public Timestamp getDelivered() {
		return delivered;
	}
	public void setDelivered(Timestamp delivered) {
		this.delivered = delivered;
	}
	public Integer getAttributes() {
		return attributes;
	}
	public void setAttributes(Integer attributes) {
		this.attributes = attributes;
	}
	
	public Timestamp getExecuteDelivery() {
		return executeDelivery;
	}
	public void setExecuteDelivery(Timestamp executeDelivery) {
		this.executeDelivery = executeDelivery;
	}
	public Integer getConfigurationId() {
		return configurationId;
	}
	public void setConfigurationId(Integer configurationId) {
		this.configurationId = configurationId;
	}
	
	
}
