package com.cb.auth;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;

public class AuthenticationService {
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		String password = "";

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		
//		DBConnector dbInstance = DBConnector.getInstance();
//		MongoDatabase dbCourseBook = dbInstance.getDatabase();
//		MongoCollection<Document> userCollection = dbCourseBook.getCollection("user_profile");
//						
//		String result = "";
//		Document doc = userCollection.find((eq("username", username))).first();
//		String originalPassword = doc.get("password").toString();
//		
		
		boolean authenticationStatus = "admin".equals(username)&& "".equals(password);
		
		return authenticationStatus;
	}
}
