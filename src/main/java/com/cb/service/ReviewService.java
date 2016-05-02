package com.cb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.cb.model.DBConnector;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("review")
public class ReviewService {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProfile(@FormParam("username") String username, @FormParam("courseno") String courseno, @FormParam("rating") String rating, @FormParam("review") String review){
		
		System.out.println("course number App server: " + courseno);
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> collection = dbCourseBook.getCollection("course_rating");
		
		Document doc = new Document("userName", username)
					.append("courseNumber", courseno)
					.append("rating", rating)
					.append("review", review);
		
		collection.insertOne(doc);

		return "{\"success\": \"true\"}";
	}
	
}
