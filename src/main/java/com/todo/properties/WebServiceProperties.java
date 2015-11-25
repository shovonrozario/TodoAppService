package com.todo.properties;

public class WebServiceProperties {
	// Base URL the Grizzly HTTP server will listen on
	public static String SERVICE_URI = "http://127.0.0.1:8080/todo-app-service/";
	
	// Package where Jersey will look for resources
	public static String JERSEY_RESOURCE_PACKAGE = "com.todo.service.resource";
	
}
