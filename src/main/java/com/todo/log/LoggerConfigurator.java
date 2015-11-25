package com.todo.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

public class LoggerConfigurator {

	private static Properties logProperties;

	public static void configure() {
		try {
			// Get the log4j.properties file from the properties folder of the 'resources' directory
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/log4j.properties");
			
			logProperties = new Properties();
			
			// Load the logging properties from the properties file 
			logProperties.load(inputStream);
			LogManager.resetConfiguration();
			PropertyConfigurator.configure(logProperties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
