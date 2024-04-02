package com.email.CinqdEmail.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.email.CinqdEmail.serviceImp.EmailServiceImp;


@Component
public class EmailSender {

	@Value("${email._maxDeliveryAttempts}")
	private int _maxDeliveryAttempts;
	
	@Value("${spring.profiles.active}")
	 private String activeProfile;
	
	@Autowired
	EmailServiceImp emailServiceImp;
	
	@Scheduled(fixedDelay = 10000)
	public void scheduleFixedDelayTask() {
		emailServiceImp.sendEmail(_maxDeliveryAttempts);
		System.out.println( "Fixed delay task - " + System.currentTimeMillis() / 1000);
	}
}

