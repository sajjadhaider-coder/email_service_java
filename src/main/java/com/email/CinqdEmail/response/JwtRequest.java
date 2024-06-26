package com.email.CinqdEmail.response;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String userId;
	private String password;
	
	//need default constructor for JSON Parsing
	public JwtRequest() {
		
	}

	public JwtRequest(String userId, String password) {
		this.setUserId(userId);
		this.setPassword(password);
		//this.setDomainName(domainName);
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}*/
}
