package com.email.CinqdEmail.response;

import java.sql.Timestamp;

public class SysEmail {



	// Fields

	private Integer messageId;
	private Integer businessId;
	private Timestamp created;
	private String sender;
	private String recipient;
	private String cc;
	private String bcc;
	private String subject;
	private String message;
	private String status;
	private Integer deliveryAttempts;
	private Timestamp delivered;
	private Integer attributes;
	private Timestamp executeDelivery;
	private Integer configurationId;

	// Constructors

	/** default constructor */
	public SysEmail() {
	}

	/** minimal constructor */
	public SysEmail(Integer businessId, Timestamp created, String sender,
			String recipient, String subject, String message, String status,
			Integer deliveryAttempts, Timestamp delivered, Integer attributes,
			 Timestamp executeDelivery,
			Integer configurationId) {
		this.businessId = businessId;
		this.created = created;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.message = message;
		this.status = status;
		this.deliveryAttempts = deliveryAttempts;
		this.delivered = delivered;
		this.attributes = attributes;
		this.executeDelivery = executeDelivery;
		this.configurationId = configurationId;
	}

	/** full constructor */
	public SysEmail(Integer businessId, Timestamp created, String sender,
			String recipient, String cc, String bcc, String subject,
			String message, String status, Integer deliveryAttempts,
			Timestamp delivered, Integer attributes,
			Timestamp executeDelivery, Integer configurationId) {
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

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getCc() {
		return this.cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return this.bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDeliveryAttempts() {
		return this.deliveryAttempts;
	}

	public void setDeliveryAttempts(Integer deliveryAttempts) {
		this.deliveryAttempts = deliveryAttempts;
	}

	public Timestamp getDelivered() {
		return this.delivered;
	}

	public void setDelivered(Timestamp delivered) {
		this.delivered = delivered;
	}

	public Integer getAttributes() {
		return this.attributes;
	}

	public void setAttributes(Integer attributes) {
		this.attributes = attributes;
	}

	public Timestamp getExecuteDelivery() {
		return this.executeDelivery;
	}

	public void setExecuteDelivery(Timestamp executeDelivery) {
		this.executeDelivery = executeDelivery;
	}

	public Integer getConfigurationId() {
		return this.configurationId;
	}

	public void setConfigurationId(Integer configurationId) {
		this.configurationId = configurationId;
	}



}
