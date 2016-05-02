package com.cb.util;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;

public class EmailDispatcher {
	public static final String ACCOUNT_SID = "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";
	public static final String API_ID = "pw3zZqfjR0SjCgphLk7Szw";
	
	public static Email setMessage(String to, String from,String toName, String fromName,String sub, String body){

		Email email = new Email();
 
	    email.addTo(to);
	    email.addToName(toName);
	    email.setFrom(from);
	    email.setSubject(sub);
	    email.setText(body);		
	    return email;
	}
    
	public static void sendEmail(Email email) throws SendGridException{
		SendGrid sendgrid = new SendGrid(ACCOUNT_SID);
		sendgrid.send(email);
	}
}
