package com.email.CinqdEmail.response;

//import org.springframework.security.core.userdetails.UserDetails;

import com.email.CinqdEmail.Entity.CinqdEmail;

public class Email {
	

	CinqdEmail email;
	JwtRequest jwtRequest;
	
	public Email(){}

	
	
	public Email(CinqdEmail email, JwtRequest jwtRequest) {
		super();
		this.email = email;
		this.jwtRequest = jwtRequest;
	}



	public CinqdEmail getEmail() {
		return email;
	}

	public void setEmail(CinqdEmail email) {
		this.email = email;
	}

	public JwtRequest getJwtRequest() {
		return jwtRequest;
	}

	public void setJwtRequest(JwtRequest jwtRequest) {
		this.jwtRequest = jwtRequest;
	}
	
	
}
