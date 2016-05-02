package com.cb.service;

import static com.mongodb.client.model.Filters.*;

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
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Path("cart")
public class CartService {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public String search(@FormParam("username") String username, @FormParam("courseno") String courseno){
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> collection = dbCourseBook.getCollection("cart");
		
		Document doc = new Document("userName", username)
				.append("classNumber", courseno)
				.append("quantity", "1");
	
		collection.insertOne(doc);
		
		return "{\"success\": \"true\"}";
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getItems(@PathParam("username") String username) {
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> collection = dbCourseBook.getCollection("cart");

		int flag = 0;
		
		MongoCursor<Document> items = collection.find(eq("userName", username)).iterator();
				
		StringBuilder result = new StringBuilder();
		result.append("[");
		
		while(items.hasNext()) {
			flag = 1;
			
			Document match = items.next();
			
			String classNumber = match.get("classNumber").toString();
			String quantity = match.get("quantity").toString();
			
			result.append("{ \"classNumber\" : \"").append(classNumber).append("\", ")
				.append("\"quantity\" : \"").append(quantity).append("\"},");
			
		}
		
		if(flag == 1)
			result = result.replace(result.length() - 1, result.length(), "");
		
		result.append("]");
		
		return result.toString();
	}
	
	@POST
	@Path("/removeFromCart")
	@Produces(MediaType.APPLICATION_JSON)
	public String remove(@FormParam("username") String username, @FormParam("classNumber") String classNumber) {
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> collection = dbCourseBook.getCollection("cart");

		int flag = 0;
				
		collection.deleteOne(and(eq("userName", username), eq("classNumber", classNumber)));
		
		return "{\"success\": \"true\"}";
	}
	
}
