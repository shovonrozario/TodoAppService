package com.todo.springconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextFactory {
	
	private static ApplicationContext context;
	
	public static ApplicationContext getInstance() {
		if(context != null)
			return context;
		
		return new AnnotationConfigApplicationContext(SpringServiceConfig.class);
	}
}
