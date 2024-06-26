package com.email.CinqdEmail.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {



	 
	  private static Pattern pattern;
	  private static Matcher matcher;

	  private static final String EMAIL_PATTERN = 
               "^[_A-Za-z0-9'+-]+(\\.[_A-Za-z0-9'+-]+)*@[A-Za-z0-9\\-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		              

	  public EmailValidator(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	  }

	  /***
	   * Validate hex with regular expression
	   * @param hex hex for validation
	   * @return true valid hex, false invalid hex
	   */
	  public static boolean validate(final String hex){
//		  final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9\\-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//		  System.out.println("Email Pattern2: " + EMAIL_PATTERN);
		  pattern = Pattern.compile(EMAIL_PATTERN);
		  matcher = pattern.matcher(hex);
		  return matcher.matches();
	  }
	  
	  public static void main(String[] args) throws Exception {
		  boolean answer = EmailValidator.validate("tengkiatkum@--yahoo--.com");
		 // System.out.println("Pass: " + answer);
	  }


}
