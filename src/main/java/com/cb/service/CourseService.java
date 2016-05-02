package com.cb.service;

import static com.mongodb.client.model.Filters.eq;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.cb.model.DBConnector;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

@Path("course")
public class CourseService {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public String search(@FormParam("searchkey") String searchkey){
		
		int flag = 0;
		
		System.out.println("Search key: " + searchkey);
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> collection = dbCourseBook.getCollection("course");
		
		MongoCursor<Document> courses = collection.find(or(eq("courseName", searchkey), eq("courseNumber", searchkey), eq("classNumber", searchkey), eq("profName", searchkey), eq("level", searchkey), eq("dept", searchkey))).iterator();
		
		StringBuilder result = new StringBuilder();
		result.append("[");
		
		while(courses.hasNext()) {
			flag = 1;
			
			Document match = courses.next();
			
			String courseName = match.get("courseName").toString();
			String courseNumber = match.get("courseNumber").toString();
			String term = match.get("term").toString();
			String classNumber = match.get("classNumber").toString();
			String classSection = match.get("classSection").toString();
			String profName = match.get("profName").toString();
			String profID = match.get("profID").toString();
			String level = match.get("level").toString();
			String dept = match.get("dept").toString();
			String classStrength = match.get("classStrength").toString();
			
			
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
