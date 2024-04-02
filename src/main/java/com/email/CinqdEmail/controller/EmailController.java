package com.email.CinqdEmail.controller;

import java.util.Base64;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.email.CinqdEmail.Entity.CinqdEmail;
//import com.email.cinqd.config.JwtTokenUtil;
import com.email.CinqdEmail.response.JwtRequest;
import com.email.CinqdEmail.response.ResponseTemplate;
import com.email.CinqdEmail.serviceImp.EmailServiceImp;
//import com.email.cinqd.serviceImp.JwtUserDetailsService;

@RequestMapping("/email")
@RestController
public class EmailController {

	@Autowired
	EmailServiceImp emailServiceImp;
	
	//@Autowired
	//private JwtUserDetailsService userDetailsService;
	/*
	@Value("${spring.security.user.name}")
	private String username;
	
	@Value("${spring.security.user.password}")
	private String password;
	*/
	
	
	
	//@Autowired
	//JwtTokenUtil jwtTokenUtil;
	
	@GetMapping(value = "/getEmailLog")
	public ResponseEntity<?> getEmailLog(HttpServletRequest request, @RequestHeader(name = "Authorization") String authorization,  @RequestParam String businessId, @RequestParam String searchInput, @RequestParam String searchField, @RequestParam Integer start,
			@RequestParam Integer size, @RequestParam String sortBy, @RequestParam String sortOrder, @RequestParam String userEmail) throws Exception {
		
		
		String userId = new String(Base64.getDecoder().decode((request.getHeader("userId"))));
		String password = new String(Base64.getDecoder().decode((request.getHeader("password"))));
		
		/*
		 * UserDetails userDetails =
		 * org.springframework.security.core.userdetails.User.builder()
		 * .username(userId) .password(password) .roles("USER","ADMIN") .build();
		 */
		
		List<CinqdEmail> emailLog = null;
		String jwtToken = authorization.substring(7);
		Boolean isValidated =true; // jwtTokenUtil.validateToken(jwtToken, userDetails);
		
		if ( true ) {//isValidated ) {
			emailLog = emailServiceImp.getSysEmailList(Integer.valueOf(businessId), searchInput, searchField, Integer.valueOf(start), Integer.valueOf(size), sortBy, sortOrder, userEmail);
		} else {
			 return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(new ResponseTemplate(0, 
					"token is invalid or session has expired"));
		}
		
		return ResponseEntity.status(HttpServletResponse.SC_OK).body(emailLog);
	}
	
	@GetMapping(value = "/getEmailByIds")
	public ResponseEntity<?> getEmailByIds(HttpServletRequest request, @RequestHeader(name = "Authorization") String authorization, 
			@RequestParam List<Integer> idList) throws Exception {
		
		List<CinqdEmail> emailList = null;
		String jwtToken = authorization.substring(7);
		int businessId = 1; //jwtTokenUtil.getBusinessIdFromToken(jwtToken);
		
		
		String userId = new String(Base64.getDecoder().decode((request.getHeader("userId"))));
		String password = new String(Base64.getDecoder().decode((request.getHeader("password"))));
		
		/*
		 * UserDetails userDetails =
		 * org.springframework.security.core.userdetails.User.builder()
		 * .username(userId) .password(password) .roles("USER","ADMIN") .build();
		 */
		
		Boolean isValidated = true; //jwtTokenUtil.validateToken(jwtToken, userDetails);
		if ( isValidated ) {
			emailList = emailServiceImp.getEmailByIds(Integer.valueOf(businessId), idList);
		} else {
			 return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(new ResponseTemplate(0, 
					"token is invalid or session has expired"));
		}
		return ResponseEntity.status(HttpServletResponse.SC_OK).body(emailList);
	}
	
	
	//@PostMapping(value="/saveEmail")
	@PostMapping(value = "/saveEmail", consumes = "application/json", produces = "application/json")
	public void saveEmail(//@RequestHeader(name = "Authorization") String authorization,
			@RequestBody CinqdEmail cinqdEmail
		//	@RequestParam String username,
		//	@RequestParam String password
			) throws Exception {
		
		
		if ( cinqdEmail == null ) {
			/*
			return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(new ResponseTemplate(0, 
					"body cannot be null"));
			*/
		}
		
		//String userId = new String(Base64.getDecoder().decode((username)));
		//String pass = new String(Base64.getDecoder().decode((password)));
		
		//SkildEmail skildEmail = email.getEmail();
		//String jwtToken = authorization.substring(7);
		JwtRequest authenticationRequest = new JwtRequest(); //email.getJwtRequest();
		//authenticationRequest.setUserId(userId);
		//authenticationRequest.setPassword(pass);
		String response = "";
		int businessId = 1 ;//jwtTokenUtil.getBusinessIdFromToken(jwtToken);
		
		/*
		 * final UserDetails userDetails =
		 * userDetailsService.loadUserByUserIdPassword(authenticationRequest.getUserId()
		 * , authenticationRequest.getPassword(), String.valueOf(businessId) , null);
		 */
		
		
		Boolean isValidated = true; //jwtTokenUtil.validateToken(jwtToken, userDetails);
		
		if ( isValidated ) {
			response = emailServiceImp.saveEmail(cinqdEmail);
		} else {
			/*
			 * return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(new
			 * ResponseTemplate(0, "token is invalid or session has expired"));
			 */
		}
		
		//return ResponseEntity.status(HttpServletResponse.SC_OK).body(response);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Your Email Service Is Working Fine";
	}
}
