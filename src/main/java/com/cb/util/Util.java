package com.cb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Util {

	static Gson gson = new Gson();
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public static Class<?> fromJson(String json, Class<?> toClass) {
		return (Class<?>) gson.fromJson(json, toClass);
	}
	
}
