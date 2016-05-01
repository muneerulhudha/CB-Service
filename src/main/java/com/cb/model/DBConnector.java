package com.cb.model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class DBConnector {
	
	private static DBConnector instance = null;
	MongoClient mongoClient;
	MongoDatabase database;

	protected DBConnector() {
		MongoClientURI connectionString = new MongoClientURI("mongodb://ujg:1234567@ds015740.mlab.com:15740/coursebook");
		mongoClient = new MongoClient(connectionString);	
		database = mongoClient.getDatabase("coursebook");
	}

	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return instance;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}
	
	public MongoDatabase getDatabase() {	
		return database;
	}
}