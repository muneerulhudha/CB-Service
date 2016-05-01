package com.cb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.cb.model.DBConnector;
import com.cb.util.Password;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

@Path("/login")
public class LoginValidationService {
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(@FormParam("username") String username, @FormParam("password") String password) throws Exception {
		
		DBConnector dbInstance = DBConnector.getInstance();
		MongoDatabase dbCourseBook = dbInstance.getDatabase();
		MongoCollection<Document> userCollection = dbCourseBook.getCollection("user_profile");
						
		String result = "";
		Document doc = userCollection.find((eq("username", username))).first();
		
		if(doc == null){
			result = "{\"success\": false, \"message\": \"Username doesn't exisit. Please check the input data\"}";			
			return Response.status(200).entity(result).build();
		}else{
			String originalPassword = doc.get("password").toString();
			
			if(Password.check(password, originalPassword)){
				result = "{\"success\": true, \"message\": \"Valid UserName Password\"}";	
				NewCookie cookie = new NewCookie(username, password);
				System.out.println(cookie.getValue());
				return Response.ok().entity(result).cookie(cookie).build();				
			}
			else{
				result = "{\"success\": false, \"message\": \"Invalid Password. Please check the input data\"}";
				return Response.status(200).entity(result).build();
			}			
		}

	}
}
