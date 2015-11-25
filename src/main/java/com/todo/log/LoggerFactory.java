package com.todo.log;

public class LoggerFactory {
	
	public static Logger getLogger(Class classToLog) {
		return new Logger(classToLog);
	}
	
}
