package com.email.CinqdEmail.serviceImp;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.email.CinqdEmail.Entity.CinqdEmail;
import com.email.CinqdEmail.Entity.CinqdEmailStaging;
import com.email.CinqdEmail.common.AmazonSES;
import com.email.CinqdEmail.repository.EmailLogRepository;
import com.email.CinqdEmail.repository.EmailRepository;
import com.email.CinqdEmail.repository.EmailStagingRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
@Component
public class EmailServiceImp {

	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@Autowired    
	private EmailRepository emailRepository; 
	
	@Autowired    
	private EmailStagingRepository emailStagingRepository; 
	
	@Autowired    
	private EmailLogRepository emailLogRepository; 
	
	@PersistenceContext // or even @Autowired
    private EntityManager entityManager;

	@Autowired    
	private AmazonSES amazonSES; 
	
	
	public void sendEmail(int _maxDeliveryAttempts) {    
		List<CinqdEmail> emailsToBeSent = null;
		List<CinqdEmailStaging> emailsToBeSentStaging = null;
		if (activeProfile.equals("prod")) {
			emailsToBeSent = emailRepository.getEmailsToSend();
		} else {
			emailsToBeSentStaging = emailStagingRepository.getEmailsToSend();
		}
		amazonSES.processEmail(emailsToBeSent, _maxDeliveryAttempts, emailsToBeSentStaging);
		//return emailsToBeSent;    
	}
	
	public List<CinqdEmail> getEmailLog() {
		List<CinqdEmail> emailLog = emailRepository.getEmailLog();
		return emailLog;
	}
	
	public String saveEmail(CinqdEmail skildEmail) {
		
		if ( activeProfile.equals("prod")) {
			emailRepository.save(skildEmail);
		} else {
			
			CinqdEmailStaging newInstance = new CinqdEmailStaging();
		    new ModelMapper().map(skildEmail, newInstance);
		    emailStagingRepository.save(newInstance);
		}
		
		return "Email save successfully";
	}
	
	public List<CinqdEmail> getEmailByIds(int businessId, List<Integer> ids) {
		
		List<CinqdEmail> emailList = null;
		List<CinqdEmailStaging> emailStagingList = null;
		if ( activeProfile.equals("prod")) {
			emailList = emailRepository.getEmailByIds(businessId, ids);
		} else {
			emailStagingList = emailStagingRepository.getEmailByIds(businessId, ids);
			emailList = new ArrayList<>();
			for (int i = 0; i < emailStagingList.size(); i++ ) {
				
				CinqdEmail newInstance = new CinqdEmail();
			    new ModelMapper().map(emailStagingList.get(i), newInstance);
			    emailList.add(newInstance);
			}
		}
		
		return emailList;
		
	}
	
	
	public List<CinqdEmail> getSysEmailList(int businessId, String searchInput,  String searchField, int start, int size, String sortBy, String sortOrder, String userEmail) throws Exception {
		List<CinqdEmail> result = null;
		
		List<CinqdEmail> emailList = null;
		
		
		if (businessId > 0) {
			StringBuffer queryString = new StringBuffer();
			
			if ( activeProfile.equals("prod")) {
				queryString.append("select * from Skild_Email where event_id =  " + businessId);
			} else {
				queryString.append("select * from Skild_Email_Staging where event_id =  " + businessId);
			}
			//search area
			
			//SysUserDAO sysuserDAO = new SysUserDAO();
			//Pattern pattern = Pattern.compile(".*[^0-9].*");
			String regex = "\\d+";
			
			if (searchInput.isEmpty()) {
				searchField = "Id";
				//searchInput = null;
			}
			
			if(!(searchInput.isEmpty()) && "UserId".equalsIgnoreCase(searchField) && !(searchInput.matches(regex)) ) {
				//searchField = null; //"Id";
				searchInput = null;
			}
			if ("UserId".equalsIgnoreCase(searchField)) {
			//SysUser sysUser = sysuserDAO.findByBusinessIdByUserId(businessId, Integer.parseInt(searchInput));
			//String userEmail = sysUser.getEmail();
			
			searchField = "Recipient";
			searchInput = userEmail;
			}
			
			if (searchInput != null && searchInput.trim().length() != 0) {
				if ("Id".equalsIgnoreCase(searchField))
					queryString.append(" and message_id like '%" + searchInput +"%'");
				else if ("Recipient".equalsIgnoreCase(searchField))
					queryString.append(" and recipient like '%" + searchInput +"%'");
				else if ("Subject".equalsIgnoreCase(searchField))
					queryString.append(" and subject like '%" + searchInput +"%'");
				else if ("Status".equalsIgnoreCase(searchField))
					queryString.append(" and status like '%" + searchInput +"%'");
				else if ("UserId".equalsIgnoreCase(searchField))
					queryString.append(" and userId like '%" + searchInput +"%'");
			}
					
			//sort area
			if(sortBy != null && sortBy.trim().length() != 0) {
				queryString.append(" order by ");

				if ("Message_Id".equalsIgnoreCase(sortBy) || "Id".equalsIgnoreCase(sortBy))
					queryString.append(" message_id ");
				else if ("Recipient".equalsIgnoreCase(sortBy))
					queryString.append(" recipient ");
				else if ("Subject".equalsIgnoreCase(sortBy))
					queryString.append(" subject ");
				else if ("Status".equalsIgnoreCase(sortBy))
					queryString.append(" status ");
				else if ("Delivered".equalsIgnoreCase(sortBy))
					queryString.append(" delivered ");
				
				queryString.append(sortOrder);
			}

			jakarta.persistence.Query query = entityManager.createNativeQuery(queryString.toString(), CinqdEmail.class);
			result = query.getResultList();
			//result = seDAO.getFlexigridData(queryString.toString(), start, size);
		}
		return result;
	}
}
