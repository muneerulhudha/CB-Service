package com.cb.service;

import static com.mongodb.client.model.Filters.eq;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.cb.model.DBConnector;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Path("history")
public class PurchaseHistoryService {

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfile(@PathParam("username") String username) {
		
		//System.out.println("Username: " + username);
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> collection = dbCourseBook.getCollection("studRegistration");
		MongoCollection<Document> courseCollection = dbCourseBook.getCollection("course");
		int flag = 0;
		
		MongoCursor<Document> classes = collection.find(eq("userName", username)).iterator();
				
		StringBuilder result = new StringBuilder();
		result.append("[");
		
		while(classes.hasNext()) {
			flag = 1;
			
			Document match = classes.next();
			
			String classNumber = match.get("classNumber").toString();
			
			Document course = courseCollection.find(eq("classNumber", classNumber)).first();
			
			String courseName = course.get("courseName").toString();
			String courseNumber = course.get("courseNumber").toString();
			String term = course.get("term").toString();
			String classSection = course.get("classSection").toString();
			String profName = course.get("profName").toString();
			String profID = course.get("profID").toString();
			String level = course.get("level").toString();
			String dept = course.get("dept").toString();
			String classStrength = course.get("classStrength").toString();
			
			result.append("{ \"courseName\" : \"").append(courseName).append("\", ")
			.append("\"courseNumber\" : \"").append(courseNumber).append("\", ")
			.append("\"term\" : \"").append(term).append("\", ")
			.append("\"classNumber\" : \"").append(classNumber).append("\", ")
			.append("\"classSection\" : \"").append(classSection).append("\", ")
			.append("\"profName\" : \"").append(profName).append("\", ")
			.append("\"profID\" : \"").append(profID).append("\", ")
			.append("\"level\" : \"").append(level).append("\", ")
			.append("\"dept\" : \"").append(dept).append("\", ")
			.append("\"seatsAvailable\" : \"").append(classStrength).append("\"},");
			
		}
		
		if(flag == 1)
			result = result.replace(result.length() - 1, result.length(), "");
		
		result.append("]");
		
		return result.toString();
	}
	
}
