package com.email.CinqdEmail.response;

import java.sql.Timestamp;

public class User implements java.io.Serializable {



	// Fields
	private Integer domainId;
	private Integer apiUserId;
	private Integer businessId;
	private String domainName;
	private String login;
	private String password;
	private Timestamp lastModified;
	private Integer modifiedBy;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Integer domainId, Integer apiUserId, Integer businessId, String domainName, Timestamp lastModified,
			Integer modifiedBy) {
		this.domainId = businessId;
		this.apiUserId = apiUserId;
		this.businessId = businessId;
		this.domainName = domainName;
		this.lastModified = lastModified;
		this.modifiedBy = modifiedBy;
	}
	
	// Property accessors
	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	public Integer getApiUserId() {
		return apiUserId;
	}

	public void setApiUserId(Integer apiUserId) {
		this.apiUserId = apiUserId;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


}
