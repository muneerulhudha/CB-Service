package com.cb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.cb.model.DBConnector;
import com.cb.util.Password;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;


@Path("register")
public class UserRegistrationService {
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(@FormParam("username") String username, @FormParam("password") String password, @FormParam("email") String email) throws Exception {
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> userCollection = dbCourseBook.getCollection("user_profile");
						
		String result = "";
		Document doc = userCollection.find(or(eq("username", username), eq("email", email))).first();
				
		if(doc == null){
			Document newDoc = new Document("username", username)
		               .append("password", Password.getSaltedHash(password))
		               .append("email", email)		               
		               .append("address", "")
		               .append("phoneno", "")
		               .append("school", "")
		               .append("dept", "");
			
			userCollection.insertOne(newDoc);
			result = "{\"success\": true}";							
			return Response.status(200).entity(result).build();
		}else{
			result = "{\"success\": false, \"message\": \"Username or Email already exists\"}";
			return Response.status(200).entity(result).build();
		}

	}
}
