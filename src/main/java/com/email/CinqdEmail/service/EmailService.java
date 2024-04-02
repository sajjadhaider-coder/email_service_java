package com.email.CinqdEmail.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.email.CinqdEmail.Entity.CinqdEmail;

@Service
public interface EmailService {

	public List<CinqdEmail> getEmailsToSend(); 
	public List<CinqdEmail> getEmailLog();
	public void saveEmail();	
	public List<CinqdEmail> getEmailByIds(int businessId, List<Integer> ids);
}
