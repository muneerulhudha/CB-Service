package com.cb.service;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.cb.model.DBConnector;
import com.cb.util.EmailDispatcher;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;

@Path("checkout")
public class CheckoutService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String checkout(@FormParam("username") String username) throws SendGridException {
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> cart = dbCourseBook.getCollection("cart");
		MongoCollection<Document> registration = dbCourseBook.getCollection("studRegistration");
		
		MongoCursor<Document> items = cart.find(eq("userName", username)).iterator();
		
		while(items.hasNext()) {
			
			Document match = items.next();
			
			String classNumber = match.get("classNumber").toString();
			
			Document newDoc = new Document("userName", username)
					.append("classNumber", classNumber);
			
			registration.insertOne(newDoc);
			
			cart.deleteOne(and(eq("userName", username), eq("classNumber", classNumber)));
			
			Email email = EmailDispatcher.setMessage("gowtham.uj@gmail.com", "admin@cb.com", "Gowtham", "CBAdmin", "Confirmation", "Your purchase order for the courses has been received.");
			EmailDispatcher.sendEmail(email);
		}
		
		return "{\"success\": \"true\"}";
	}
	
}
