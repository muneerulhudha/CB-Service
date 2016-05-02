package com.cb.service;

import static com.mongodb.client.model.Filters.eq;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.cb.model.DBConnector;
import com.cb.model.Profile;
import com.cb.util.Util;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProfile(@FormParam("username") String username, @FormParam("email") String email, @FormParam("name") String name, @FormParam("address") String address, @FormParam("phoneno") String phoneno, @FormParam("dept") String dept){
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> userCollection = dbCourseBook.getCollection("user_profile");
		
		Document existingDoc = userCollection.find(eq("username", username)).first();
		
		Document doc = new Document("username", username)
					.append("password", existingDoc.get("password"))
					.append("email", email)
					.append("name", name)
					.append("dept", dept)
	                .append("address", address)
	                .append("phoneno", phoneno);
		
		userCollection.updateOne(eq("username", username), new Document("$set", doc));
		
		return "{\"success\": \"true\"}";
	}
	
}
