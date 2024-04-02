package com.email.CinqdEmail.response;

//import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class ResponseTemplate {
	
	private String message;
	private int success;
	
	public ResponseTemplate(int success, String message) {
		this.message = message;
		this.success = success;
	}
	
	public ResponseTemplate() {
		super();
	}
	
	public ResponseTemplate(int success) {
		this.success = success;
	}
	
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
