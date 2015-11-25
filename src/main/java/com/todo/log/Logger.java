package com.todo.log;

public class Logger {
	
	private org.apache.log4j.Logger logger;
	
	public Logger(Class classToLog) {
		logger = org.apache.log4j.Logger.getLogger(classToLog);
	}
	
	public void log(String message) {
		logger.info(message);
	}
}
