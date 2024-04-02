package com.email.CinqdEmail.common;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.regions.Region;
//import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.RegionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.email.CinqdEmail.Entity.CinqdEmail;
import com.email.CinqdEmail.Entity.CinqdEmailLog;
import com.email.CinqdEmail.Entity.CinqdEmailLogStaging;
import com.email.CinqdEmail.Entity.CinqdEmailStaging;
import com.email.CinqdEmail.Entity.EmailLogId;
import com.email.CinqdEmail.repository.EmailLogRepository;
import com.email.CinqdEmail.repository.EmailLogStagingRepository;
import com.email.CinqdEmail.repository.EmailRepository;
import com.email.CinqdEmail.repository.EmailStagingRepository;
import com.email.CinqdEmail.response.SysEmail;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;

@Component
public class AmazonSES {
	
	@Value("${staging.server.url}")
	private String stagingServer;

	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@Autowired    
	private EmailStagingRepository emailStagingRepository; 
	
	@Autowired    
	private EmailRepository emailRepository; 
	
	@Autowired    
	private EmailLogRepository emailLogRepository; 
	
	@Autowired    
	private EmailLogStagingRepository emailLogStagingRepository; 
	
	
	private String sendingError="";
	private static AmazonSimpleEmailService ses;
	private static AmazonSES amazonSes = null;

	public static AmazonSES getInstance(){
		if (amazonSes == null)
			ses = new AmazonSimpleEmailServiceClient(new BasicAWSCredentials(accessKey, secretKey)); 
			Region region = Region.getRegion(Regions.EU_WEST_2); 
			ses.setRegion(region);
		amazonSes = new AmazonSES();
		return amazonSes;
	}
	
	// This is for email spacing issue
	public static String replaceSpaces(String str) {
		
		str = str.replaceAll("\n\n", "\n");
	
	return str;
}
	// This is for email spacing issue
	public static String replacePara(String str) {
		str = str.replaceAll("<p>", " <p style = \"margin-top: 0px; margin-bottom: 0px;\" > ");
		str = str.replaceAll("<p style = \"margin-bottom: -1em !important;\" >", " <p style = \"margin-top: 0px; margin-bottom: 0px;\" > ");
		
	return str;
}


	public void sendMessage(SysEmail email) throws Exception  {   

		try {
			String emailAddWithFormat = email.getRecipient();
		    
			byte ptext[] = emailAddWithFormat.getBytes(Charset.forName("UTF-8")); 
			String toEmailAdd = new String(ptext, Charset.forName("UTF-8")); 
			
			SendEmailRequest request = new SendEmailRequest().withSource(email.getSender());		
			List<String> toAddresses = new ArrayList<String>();
			
			toAddresses.add(toEmailAdd);
			Destination dest = new Destination().withToAddresses(toAddresses);
			request.setDestination(dest);

			Content subjContent = new Content().withData(email.getSubject());
			Message msg = new Message().withSubject(subjContent);
			String  message = this.replacePara(email.getMessage());
			Content htmlContent = new Content().withData(message);
		
			Content textContent = new Content().withData("");
			Body body = new Body().withHtml(htmlContent).withText(textContent);
			msg.setBody(body);

			request.setMessage(msg);

			getInstance();
			ses.sendEmail(request);
			email.setStatus("sent");
		}
		catch (AmazonClientException e) {
			email.setStatus("error");
			this.setSendingError(e.getMessage());
			//			System.out.println("Message: " + e.getMessage());// address exception then save this
			this.setSendingError(e.getMessage());
			//			System.out.println("Local Message: " + e.getLocalizedMessage());
			//			System.out.println("Caught an AddressException, which means one or more of your "
			//					+ "addresses are improperly formatted.");
		} 
		catch (Exception e) {
			email.setStatus("error");
			StringBuffer sb = new StringBuffer("Email Id: " + email.getMessageId());
			//			System.out.println("Message: " + e.getMessage());
			sb.append(" | " + e.getMessage());// message exception then save this
			this.setSendingError(sb.toString());
		}
	}

	public static boolean emailVerified(String address) throws Exception {
		getInstance();
		return emailVerified(ses, address);
	}

	public static boolean emailVerified(AmazonSimpleEmailService ses, String address) throws Exception {
		boolean result = false;
		if ( address != null || address.trim().length() != 0) {
			ListVerifiedEmailAddressesResult verifiedEmails = ses.listVerifiedEmailAddresses();
			if (verifiedEmails.getVerifiedEmailAddresses().contains(address))
				result = true;
		}
		return result;
	}

	public static void verifyEmailAddress(String address) throws Exception {
		getInstance();
		verifyEmailAddress(ses, address);
	}

	public static void verifyEmailAddress(AmazonSimpleEmailService ses, String address) throws Exception {
		if (!emailVerified(ses, address) && EmailValidator.validate(address)) 
			ses.verifyEmailAddress(new VerifyEmailAddressRequest().withEmailAddress(address));
	}


	public void sendMessage(CinqdEmail email) throws Exception  {   

		try {
			SysEmail emailCommon = new SysEmail(); 
			
			emailCommon.setMessageId(email.getMessageId());
			emailCommon.setBusinessId(email.getBusinessId());
			emailCommon.setCreated(email.getCreated());
			emailCommon.setSender(email.getSender());
			emailCommon.setRecipient(email.getRecipient());
			emailCommon.setCc(email.getCc());
			emailCommon.setBcc(email.getBcc());
			emailCommon.setSubject(email.getSubject());
			emailCommon.setMessage(this.replaceParagraphwithLineBreak(email.getMessage()));
			emailCommon.setStatus(email.getStatus());
			emailCommon.setDeliveryAttempts(email.getDeliveryAttempts());
			emailCommon.setDelivered(email.getDelivered());
			emailCommon.setAttributes(email.getAttributes());
			emailCommon.setExecuteDelivery(email.getExecuteDelivery());
			emailCommon.setConfigurationId(email.getConfigurationId());
			
			this.sendMessage(emailCommon);
			
			email.setStatus("sent");
		} catch (Exception e) {
			email.setStatus("error");
			this.setSendingError(e.getMessage());
		}
	}
	
	private String replaceParagraphwithLineBreak(String email) {
		String result = email.replaceAll("<p>", "");
		result =  result.replaceAll("</p>", "<br>");
		result =  result.replaceAll("&nbsp;", "");
		//result = result.replaceAll("(?m)^[ \t]*\r?\n", "");
		return result;
		
	}
	
	
	public synchronized void processEmail(List<CinqdEmail> lstEmails, int maxAttempts, List<CinqdEmailStaging> emailsToBeSentStaging){
		try {
			
			if ((lstEmails!=null && lstEmails.size() > 0) || emailsToBeSentStaging!=null && emailsToBeSentStaging.size() > 0) {
				if (lstEmails == null || lstEmails.isEmpty()) {
					lstEmails = new ArrayList<>();
					for (int i = 0; i < emailsToBeSentStaging.size();  i++ ) {
						CinqdEmail se = new CinqdEmail();
						new ModelMapper().map(emailsToBeSentStaging.get(i), se);
					    lstEmails.add(se);
					}
				}
				for (CinqdEmail email : lstEmails) {
					try {
						if(email.getDeliveryAttempts() <= maxAttempts){
							sendMessage(email);
							
							email.setDeliveryAttempts(email.getDeliveryAttempts()+1);
							if(email.getDeliveryAttempts() == maxAttempts && email.getStatus() == "unsent"){
								email.setStatus("give up");
							}
							
							//saveEmail(email);
							//emailRepository.save(email);

							if ( activeProfile.equals("prod")) {
								emailRepository.save(email);
							} else {
								
								CinqdEmailStaging newInstance = new CinqdEmailStaging();
							    new ModelMapper().map(email, newInstance);
							    emailStagingRepository.save(newInstance);
							}
							
							if(!"sent".equalsIgnoreCase(email.getStatus())) {
								CinqdEmailLog sysEmailLog = new CinqdEmailLog();
								sysEmailLog.setStatus("sending failure");
								sysEmailLog.setErrorMessage(this.getSendingError());
								EmailLogId sysEmailLogId = new EmailLogId();
								sysEmailLogId.setMessageId(email.getMessageId());
								sysEmailLogId.setDeliveryAttempt(new  Timestamp(new Date().getTime()));
								sysEmailLog.setId(sysEmailLogId);
								//saveEmailLog(sysEmailLog);
								
								if ( activeProfile.equals("prod")) {
									emailLogRepository.save(sysEmailLog);
								} else {
									
									CinqdEmailLogStaging newInstance = new CinqdEmailLogStaging();
								    new ModelMapper().map(sysEmailLog, newInstance);
								    emailLogStagingRepository.save(newInstance);
								}
								
								emailLogRepository.save(sysEmailLog);
							}
						}
						// save sysemail log as well
					}catch (Exception e) {
						email.setStatus("error");
						this.setSendingError(e.getMessage());
					}

				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void saveEmail(CinqdEmail email) throws Exception {
		//DAOFactory df = DAOFactory.getInstance();
		//SysEmailDAO sysEmailDAO = df.createSysEmailDAO();
		//SysEmailLogDAO sysEmailLogDAO = df.createSysEmailLogDAO();
		//sysEmailDAO.saveOrUpdate(email);
		emailRepository.save(email);
	}

	public void saveEmailLog(CinqdEmailLog emailLog) throws Exception {
		// df = DAOFactory.getInstance();
		//SysEmailLogDAO sysEmailLogDAO = df.createSysEmailLogDAO();
		//sysEmailLogDAO.saveOrUpdate(emailLog);
		emailLogRepository.save(emailLog);
	}

	public String getSendingError() {
		return sendingError;
	}

	public void setSendingError(String sendingError) {
		this.sendingError = sendingError;
	}

	public static void main(String[] args) {
		
		
	}

}
