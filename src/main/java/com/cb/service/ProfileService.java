package com.cb.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.bson.Document;

import com.cb.model.DBConnector;
import com.cb.model.Profile;
import com.cb.util.Util;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

@Path("profile")
public class ProfileService {

	@GET
	@Path("/{username}")
	public String getProfile(@PathParam("username") String username) {
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> userCollection = dbCourseBook.getCollection("user_profile");
		
		Document doc = userCollection.find(eq("username", username)).first();
		
		Profile profile = new Profile(doc.get("username").toString(), doc.get("name").toString(), doc.get("password").toString(), doc.get("email").toString(), doc.get("dept").toString(), doc.get("address").toString(), doc.get("phoneno").toString(), doc.get("lastLoginTime").toString());
		
		return Util.toJson(profile);
	}
	
}
